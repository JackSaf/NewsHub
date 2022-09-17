package com.jacksafblaze.newshub.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jacksafblaze.newshub.R

class SeparatorViewHolder(private val view: View): ViewHolder(view) {
    private val publishTime: TextView = view.findViewById(R.id.publish_time)

    fun bind(publishTimeText: String){
        publishTime.text = publishTimeText
    }
    companion object{
        fun create(parent: ViewGroup): SeparatorViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.separator_view_item, parent, false)
            return SeparatorViewHolder(view)
        }
    }
}