package com.eslam.callkt.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eslam.callkt.databinding.FragmentHomeBinding


class HomeActivityFragment : Fragment() {


    private val viewModel: HomeViewModel by lazy {

        val factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(HomeViewModel::class.java))
                    return HomeViewModel(activity!!.application) as T
                throw IllegalArgumentException("unknown view model.")
            }

        }
        ViewModelProvider(this, factory).get(HomeViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHomeBinding.inflate(layoutInflater)

        initViewModel(binding)

        return binding.root
    }

    private fun initViewModel(binding: FragmentHomeBinding) {
        binding.viewModel = viewModel

        binding.executePendingBindings()

        binding.lifecycleOwner = this


    }


}



