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
import com.mmm.moviedb.adapters.Popular_Adapt
import com.mmm.moviedb.model.ResultsItem
import com.mmm.moviedb.viewmodel.Popular_VM
import kotlinx.android.synthetic.main.fragment_popular.*

class PopularFragment : Fragment(),Popular_Adapt.ClickListener{
    lateinit var popular_adapt : Popular_Adapt
    lateinit var popular_vm : Popular_VM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popular_adapt= Popular_Adapt()
        popular_vm = ViewModelProvider(this).get(Popular_VM::class.java)
        popular_recycler.layoutManager=GridLayoutManager(context,2)
        popular_recycler.adapter=popular_adapt
        popular_vm.getPopular().observe(viewLifecycleOwner, Observer {
            popular_adapt.update_items(it.results as List<ResultsItem>)
        })
        popular_adapt.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        popular_vm.loadPopular()
    }

    override fun onClick(result: ResultsItem) {
        var action=PopularFragmentDirections.actionPopularFragment2ToDetailsFragment(result.id.toString())
        findNavController().navigate(action)
    }
}