package com.mmm.moviedb.TopRated

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mmm.moviedb.R
import com.mmm.moviedb.model.ResultsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_toprated.view.*
import java.util.ArrayList

class Toprated_adapter(var TopRatedList : List<ResultsItem> = ArrayList<ResultsItem>()) : RecyclerView.Adapter<Toprated_adapter.viewholder>(){

    var pClickListener : ClickListener?= null

    fun setOnClickListener(clickListener: ClickListener){
        this.pClickListener = clickListener
    }

    inner class viewholder(itemView : View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener{

        lateinit var resultsItem: ResultsItem

        init {
            itemView.setOnClickListener(this)
        }


        fun bind(resultsItem: ResultsItem) {
            this.resultsItem = resultsItem
            Picasso.get().load("https://image.tmdb.org/t/p/w500/" + resultsItem.posterPath)
                .into(itemView.imgtoprated)
            itemView.txttoprated.text = resultsItem.originalTitle
        }


        override fun onClick(p0: View?) {
            pClickListener?.onClick(resultsItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_toprated, parent, false)
        return viewholder(view)
    }

    override fun getItemCount(): Int {
        return TopRatedList.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.bind(TopRatedList[position])
    }

    fun updateTopRated(topRatedList: List<ResultsItem>) {
        this.TopRatedList = topRatedList
        notifyDataSetChanged()
    }

    interface ClickListener{
        fun onClick(result: ResultsItem)
    }
}