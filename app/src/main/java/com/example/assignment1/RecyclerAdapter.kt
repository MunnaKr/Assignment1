package com.example.assignment1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment1.databinding.ItemUserDataLayoutBinding

class RecyclerAdapter(dataList: List<UserDataModel>, cellClickInterface:CellClickInterface) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var dataList : List<UserDataModel> = dataList
    var cellClickInterface:CellClickInterface = cellClickInterface

    inner class ViewHolder(val binding: ItemUserDataLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserDataLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.textViewFirstName.text = dataList[position].firstName
        holder.binding.textViewLastName.text = dataList[position].lastName
        holder.binding.textViewMobile.text = dataList[position].mobile
        holder.binding.textViewAddress.text = dataList[position].address
        holder.binding.imgRemove.setOnClickListener{
            cellClickInterface.onCellClick(position)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(dataList: List<UserDataModel>, cellClickInterface:CellClickInterface){
        this.dataList = dataList
        this.cellClickInterface = cellClickInterface
        notifyDataSetChanged()
    }
}