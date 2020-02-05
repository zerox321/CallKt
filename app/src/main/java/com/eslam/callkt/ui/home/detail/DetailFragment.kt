package com.eslam.callkt.ui.home.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

import com.eslam.callkt.R
import androidx.lifecycle.ViewModel
import com.eslam.callkt.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by lazy {
        val factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(DetailViewModel::class.java))
                    return DetailViewModel() as T
                throw IllegalArgumentException("unknown view model.")
            }
        }
        ViewModelProvider(this, factory).get(DetailViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: DetailFragmentBinding = DetailFragmentBinding.inflate(layoutInflater)

        initViewModel(binding)

        return binding.root
    }
    private fun initViewModel(binding: DetailFragmentBinding) {
        binding.viewModel = viewModel
        binding.executePendingBindings()
        binding.lifecycleOwner = this
    }

}



