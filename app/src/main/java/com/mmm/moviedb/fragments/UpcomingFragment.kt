package com.mmm.moviedb.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mmm.moviedb.R
import com.mmm.moviedb.TopRated.Toprated_adapter
import com.mmm.moviedb.TopRated.Toprated_viewModel
import com.mmm.moviedb.UpComing.Upcoming_adapter
import com.mmm.moviedb.UpComing.Upcoming_viewmodel
import com.mmm.moviedb.model.ResultsItem
import kotlinx.android.synthetic.main.fragment_top_rated.*
import kotlinx.android.synthetic.main.fragment_upcoming.*

class UpcomingFragment : Fragment() , Upcoming_adapter.ClickListener {

    lateinit var upcomingAdapter: Upcoming_adapter
    lateinit var upcomingViewmodel: Upcoming_viewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        upcomingViewmodel = ViewModelProvider(this).get(Upcoming_viewmodel::class.java)
        upcomingAdapter = Upcoming_adapter()

        nav_upcoming.apply {
            layoutManager = GridLayoutManager(context , 2)
            adapter = upcomingAdapter
        }

        upcomingViewmodel.getUpComing().observe(
            viewLifecycleOwner, Observer {
                upcomingAdapter.updateTopRated(it.results as List<ResultsItem>)
            }
        )

        upcomingAdapter.setOnClickListener(this)
    }


    override fun onResume() {
        super.onResume()

        upcomingViewmodel.loadUpComing()
    }

    override fun onClick(result: ResultsItem) {
        var action = UpcomingFragmentDirections.actionUpcomingFragment2ToDetailsFragment(result.id.toString())
        findNavController().navigate(action)
    }
}
