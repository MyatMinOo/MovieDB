package com.mmm.moviedb.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mmm.moviedb.R
import com.mmm.moviedb.details.DetailsViewModel
import com.mmm.moviedb.model.Details
import com.mmm.moviedb.model.ResultsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_details.*
import java.util.ArrayList

class DetailsFragment : Fragment() {
    lateinit var detailsViewModel: DetailsViewModel
    lateinit var id : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var messageArgs = arguments?.let {
            DetailsFragmentArgs.fromBundle(it)
        }
        id = messageArgs?.id.toString()

        detailsViewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        detailsViewModel.loadDetails(id)
        detailsViewModel.getDetails().observe(viewLifecycleOwner, Observer {
            details_title.text = it.originalTitle
            details_overview.text = it.overview
            Picasso.get().load("https://image.tmdb.org/t/p/w500/"+it.backdropPath).into(details_backdrop_poster)
            Picasso.get().load("https://image.tmdb.org/t/p/w500/"+it.posterPath).into(details_poster)
            details_budget.text = it.budget.toString()
            details_status.text = it.status
            details_language.text = it.originalLanguage
            details_popularity.text = it.popularity.toString()+" Popularity"
            details_release_date.text = it.releaseDate
            details_runtime.text = it.runtime.toString() + "min"
        })


      detailsViewModel.getLoading().observe(viewLifecycleOwner, Observer {
          isLoading -> progressBar.visibility = if(isLoading) View.VISIBLE else View.GONE
      })

      detailsViewModel.getErrorStatus().observe(viewLifecycleOwner, Observer {
          status -> if (status){
          detailsViewModel.getErrorMessage().observe(viewLifecycleOwner, Observer {
              tv_errorMsg.text = it
          })
      }
      })

    }

    override fun onResume() {
        super.onResume()
        var messageArgs = arguments?.let {
            DetailsFragmentArgs.fromBundle(it)
        }
        id = messageArgs?.id.toString()
        detailsViewModel.loadDetails(id)
    }
}
