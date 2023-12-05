package com.example.randomperson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.randomperson.databinding.ItemBinding
import com.example.randomperson.model.PersonListModel

class PersonListAdapter() : RecyclerView.Adapter<PersonListAdapter.ViewHolder>() {

    private var onClickListener: OnClickListener? = null

    var list: List<PersonListModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonListAdapter.ViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.itemName.text = item.name

        holder.itemName.setOnClickListener {
            if (onClickListener != null) {
                onClickListener!!.onClick(position, item)
            }
        }
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(position: Int, model: PersonListModel)
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemName = binding.name
    }
}