package com.example.postrequest

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class RecyclerViewAdapter(var userData:List<UserDetails>) : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

    val TAG = "Adapter"
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent,false))
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val info = userData[position]
        Log.d(TAG, "onBindViewHolder: ${info.name}")

        holder.itemView.apply {
           nameTv.text = info.name
            locationTv.text = info.location

        }
    }

    override fun getItemCount() = userData.size
}
