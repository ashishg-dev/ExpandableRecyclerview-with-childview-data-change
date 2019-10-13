package com.ashish.expandablerecyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ashish.expandablerecyclerview.R
import com.ashish.expandablerecyclerview.model.Songs
import kotlinx.android.synthetic.main.lsv_child_view.view.*

class SongsNameChildAdapter(val context: Context, val data: ArrayList<Songs>) :
    RecyclerView.Adapter<SongsNameChildAdapter.ViewHolder>() {

    private lateinit var onItemClickListener : OnItemClickListener



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.lsv_child_view, parent, false);
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.textViewSongName.text = data[position].songName
    }

    fun setOnItemClickListener(onItemClickListener : OnItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    inner class ViewHolder : RecyclerView.ViewHolder {

        constructor(itemView: View) : super(itemView) {
            itemView.setOnClickListener {

                onItemClickListener.onItemClicked(adapterPosition)

            }

        }

    }

    interface OnItemClickListener {

        fun onItemClicked(childPosition: Int)

    }

}