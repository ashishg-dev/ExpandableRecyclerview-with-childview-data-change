package com.ashish.expandablerecyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ashish.expandablerecyclerview.R
import com.ashish.expandablerecyclerview.model.HeroDetails
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.lsv_parent_view.view.*

class HeroNameParentAdapter(val context: Context, val data: ArrayList<HeroDetails>) :
    RecyclerView.Adapter<HeroNameParentAdapter.ViewHolder>() {

    private lateinit var onItemClickListener: OnParentItemClickListener

    private var arrayListSongsNameChildAdapter = ArrayList<SongsNameChildAdapter>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.lsv_parent_view, parent, false);
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.textViewHeroName.text = data[position].heroName
        Picasso.get().load(data[position].heroImage).into(holder.itemView.imageViewHero)

        val songsNameChildAdapter = SongsNameChildAdapter(context, data[position].songsDetails)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        holder.itemView.recyclerViewChildData.layoutManager = layoutManager
        holder.itemView.recyclerViewChildData.adapter = songsNameChildAdapter

        songsNameChildAdapter.setOnItemClickListener(object :
            SongsNameChildAdapter.OnItemClickListener {

            override fun onItemClicked(childPosition: Int) {
                onItemClickListener.onItemClicked(position, childPosition)
            }

        })

        arrayListSongsNameChildAdapter.add(songsNameChildAdapter)

        holder.itemView.setOnClickListener {

            if (holder.itemView.recyclerViewChildData.visibility == View.VISIBLE) {
                holder.itemView.recyclerViewChildData.visibility = View.GONE
                holder.itemView.imageViewArrow.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        android.R.drawable.arrow_down_float
                    )
                )
            } else {
                holder.itemView.recyclerViewChildData.visibility = View.VISIBLE
                holder.itemView.imageViewArrow.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        android.R.drawable.arrow_up_float
                    )
                )
            }

        }

    }

    fun setOnItemClickListener(onItemClickListener: OnParentItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    fun notifyChildItemChanged(parentPosition: Int, childPosition: Int) {

        arrayListSongsNameChildAdapter[parentPosition].notifyItemChanged(childPosition)

    }

    inner class ViewHolder : RecyclerView.ViewHolder {

        constructor(itemView: View) : super(itemView) {
            itemView.setOnClickListener {

            }

        }

    }

    interface OnParentItemClickListener {

        fun onItemClicked(parentPosition: Int, childPosition: Int)

    }

}