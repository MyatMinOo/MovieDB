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
import com.mmm.moviedb.model.ResultsItem
import kotlinx.android.synthetic.main.fragment_top_rated.*

class Top_ratedFragment : Fragment() , Toprated_adapter.ClickListener{

    lateinit var toprated_viewModel: Toprated_viewModel
    lateinit var toprated_adapter: Toprated_adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rated, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toprated_viewModel = ViewModelProvider(this).get(Toprated_viewModel::class.java)
        toprated_adapter = Toprated_adapter()

        nav_topRated.apply {
            layoutManager = GridLayoutManager(context , 2)
            adapter = toprated_adapter
        }

        toprated_viewModel.getTopRated().observe(
            viewLifecycleOwner, Observer {
                toprated_adapter.updateTopRated(it.results as List<ResultsItem>)
            }
        )

        toprated_adapter.setOnClickListener(this)
    }


    override fun onResume() {
        super.onResume()

        toprated_viewModel.loadTopRated()
    }

    override fun onClick(result: ResultsItem) {
        var id = result.id
        var action = Top_ratedFragmentDirections.actionTopRatedFragment2ToDetailsFragment(id.toString())
        findNavController().navigate(action)
    }
}