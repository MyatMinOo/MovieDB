package com.mmm.moviedb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mmm.moviedb.R
import com.mmm.moviedb.model.ResultsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.now_playing_items.view.*

class Now_Playing_Adapt(var now_play_list : List<ResultsItem> = ArrayList<ResultsItem>()) : RecyclerView.Adapter<Now_Playing_Adapt.Now_Playing_Holder>(){
    var nowClickListener : ClickListener? = null

    interface ClickListener {
        fun onClick(result : ResultsItem)
    }
    fun setOnClickListener(clickListener : ClickListener)
    {
        this.nowClickListener=clickListener
    }

    inner class Now_Playing_Holder(itemView : View) : RecyclerView.ViewHolder(itemView),View.OnClickListener
    {
        lateinit var resultsItem : ResultsItem
        init {
            itemView.setOnClickListener(this)
        }
        fun bind(resultsItem : ResultsItem)
        {
            this.resultsItem=resultsItem
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/"+resultsItem.backdropPath)
                .into(itemView.now_playing_image)
            itemView.now_playing_title.text=resultsItem.title
        }

        override fun onClick(now: View?) {
            nowClickListener?.onClick(resultsItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Now_Playing_Holder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.now_playing_items,parent,false)
        return Now_Playing_Holder(view)
    }
    override fun onBindViewHolder(holder: Now_Playing_Holder, position: Int) {
        holder.bind(now_play_list.get(position))
    }
    fun updateItems(now_playing_item: List<ResultsItem>)
    {
        this.now_play_list=now_playing_item
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return now_play_list.size
    }
}