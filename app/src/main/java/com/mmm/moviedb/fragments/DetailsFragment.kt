package com.mmm.moviedb.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mmm.moviedb.Detail.DetailViewModel
import com.mmm.moviedb.R
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {

    lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Detail
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        var messageDetail = arguments?.let { DetailsFragmentArgs.fromBundle(it) }
        var Detailid: String = messageDetail!!.id
    }

//    private fun detailViewModel(movieid: String) {
//        detailViewModel.loaddetail(movieid)
//        detailViewModel.getdetail().observe(
//            viewLifecycleOwner, Observer { detail ->
//                txtTitle.text = detail.title
//                txtOriginTitle.text = detail.title
//                Picasso.get().load("https://image.tmdb.org/t/p/w500/" + detail.poster_path)
//                    .into(imgDetail)
//                txtreleasedate.text = detail.release_date
//                txtstatus.text = detail.status
//                txtgenres.text = detail.genres[0].name
//                txtpopularity.text = detail.popularity.toString()
//                txtoverview.text = detail.overview
//            }
//        )
//    }
}