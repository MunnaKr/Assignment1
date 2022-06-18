package com.example.assignment1

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), View.OnClickListener, CellClickInterface {
    lateinit var binding: ActivityMainBinding
    lateinit var dataList: ArrayList<UserDataModel>
    lateinit var cellClickInterface: CellClickInterface
    lateinit var adapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.saveButton.setOnClickListener(this)
        binding.listButton.setOnClickListener(this)
        binding.backButton.setOnClickListener(this)

        this.title = getString(R.string.registration)

        cellClickInterface = (this)

        dataList = ArrayList()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.saveButton -> saveData()

            R.id.listButton -> {
                binding.relativeList.visibility = View.VISIBLE
                binding.linearRegister.visibility = View.GONE
                if (dataList.isEmpty()) {
                    binding.textViewNoRecordFound.visibility = View.VISIBLE
                    binding.linearTableRow.visibility = View.GONE
                } else {
                    binding.textViewNoRecordFound.visibility = View.GONE
                    binding.linearTableRow.visibility = View.VISIBLE
                    showAllData()
                }
                this.title = getString(R.string.list)
                val imm: InputMethodManager =
                    this.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)
            }

            R.id.backButton -> {
                binding.relativeList.visibility = View.GONE
                binding.linearRegister.visibility = View.VISIBLE
                this.title = getString(R.string.registration)
            }

        }
    }

    fun saveData() {

        dataList.add(
            UserDataModel(
                binding.firstName.text.toString(),
                binding.lastName.text.toString(),
                binding.mobileNumber.text.toString(),
                binding.address.text.toString()
            )
        )

        Toast.makeText(this, getString(R.string.record_successfully_saved), Toast.LENGTH_SHORT)
            .show()

        binding.firstName.setText("")
        binding.lastName.setText("")
        binding.mobileNumber.setText("")
        binding.address.setText("")
    }


    override fun onBackPressed() {
        if (binding.relativeList.isVisible) {
            binding.relativeList.visibility = View.GONE
            binding.linearRegister.visibility = View.VISIBLE
        } else {
            super.onBackPressed()
        }
    }

    override fun onCellClick(position: Int) {
        dataList.removeAt(position)
        if (dataList.isEmpty())
        {
    binding.textViewNoRecordFound.visibility = View.VISIBLE
    binding.linearTableRow.visibility = View.GONE
        }
        else {
            binding.textViewNoRecordFound.visibility = View.GONE
            binding.linearTableRow.visibility = View.VISIBLE
        }
        adapter.updateData(dataList, cellClickInterface)

    }

    fun showAllData(){
        adapter = RecyclerAdapter(dataList, cellClickInterface)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = adapter

    }


}