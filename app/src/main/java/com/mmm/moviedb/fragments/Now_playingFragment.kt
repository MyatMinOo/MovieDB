package com.mmm.moviedb.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mmm.moviedb.R
import com.mmm.moviedb.adapters.Now_Playing_Adapt
import com.mmm.moviedb.model.ResultsItem
import com.mmm.moviedb.viewmodel.Now_Playing_VM
import kotlinx.android.synthetic.main.fragment_now_playing.*

class Now_playingFragment : Fragment(),Now_Playing_Adapt.ClickListener {
    lateinit var now_play_viewmodel : Now_Playing_VM
    lateinit var now_play_adapt : Now_Playing_Adapt
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_now_playing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        now_play_viewmodel = ViewModelProvider(this).get(Now_Playing_VM::class.java)
        now_play_adapt= Now_Playing_Adapt()
        now_playing_recycler.layoutManager=GridLayoutManager(context,2)
        now_playing_recycler.adapter=now_play_adapt
        now_play_viewmodel.getNowPlayingMovie().observe(viewLifecycleOwner, Observer { movies ->
            now_play_adapt.updateItems(movies.results as List<ResultsItem>)
        })
        now_play_adapt.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        now_play_viewmodel.loadNowPlayingMovie()
    }

    override fun onClick(result: ResultsItem) {
        var action = Now_playingFragmentDirections.actionNowPlayingFragment2ToDetailsFragment(result.id.toString())
        findNavController().navigate(action)
    }
}