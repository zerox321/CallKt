package com.eslam.callkt.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.eslam.callkt.databinding.FragmentHomeBinding
import com.eslam.callkt.room.RoomDB
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class HomeActivityFragment : Fragment() {


    private val viewModel: HomeViewModel by lazy {
        val dao = RoomDB.getInstance(requireContext()).dao

        val factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(HomeViewModel::class.java))
                    return HomeViewModel(activity!!.application, dao) as T
                throw IllegalArgumentException("unknown view model.")
            }
        }
        ViewModelProvider(this, factory).get(HomeViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

        initViewModel(binding)

        return binding.root
    }

    private fun initViewModel(binding: FragmentHomeBinding) {
        binding.viewModel = viewModel
        binding.executePendingBindings()
        binding.lifecycleOwner = this

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.clientsList.collect {
                viewModel.adapter.submitList(it)
            }
        }
    }

}



