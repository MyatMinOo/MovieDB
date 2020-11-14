package com.mmm.moviedb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mmm.moviedb.R
import com.mmm.moviedb.model.ResultsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.popular_items.view.*

class Popular_Adapt(var poular_list : List<ResultsItem> = ArrayList<ResultsItem>()) : RecyclerView.Adapter<Popular_Adapt.Popular_VH>(){
    var popuClickListener : ClickListener? =null

    interface ClickListener {
        fun onClick(result : ResultsItem)
    }
    fun setOnClickListener(clickListener : ClickListener)
    {
        this.popuClickListener=clickListener
    }

    inner class Popular_VH(itemView : View) : RecyclerView.ViewHolder(itemView),View.OnClickListener
    {
        lateinit var resultsItem : ResultsItem
        init {
            itemView.setOnClickListener(this)
        }
        fun bind(resultsItem : ResultsItem)
        {
            this.resultsItem=resultsItem
            Picasso.get()
                .load("https://image.tmdb.org/t/p/w500/" + resultsItem.posterPath)
                .into(itemView.popular_image)
            itemView.popular_title.text = resultsItem.title
            itemView.popular_vote.text = resultsItem.voteCount.toString()
        }
        override fun onClick(popu: View?) {
            popuClickListener?.onClick(resultsItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Popular_VH {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.popular_items,parent,false)
        return Popular_VH(view)
    }

    override fun onBindViewHolder(holder: Popular_VH, position: Int) {
        holder.bind(poular_list[position])
    }

    override fun getItemCount(): Int {
        return poular_list.size
    }
    fun update_items(popular_items : List<ResultsItem>)
    {
        this.poular_list=popular_items
        notifyDataSetChanged()
    }
}