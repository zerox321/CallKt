package com.eslam.callkt.ui.home.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.eslam.callkt.R
import com.eslam.callkt.databinding.DetailFragmentBinding
import com.eslam.callkt.room.RoomDB
import com.leinardi.android.speeddial.SpeedDialView

class DetailFragment : Fragment() {
    private val args: DetailFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by lazy {
        val dao = RoomDB.getInstance(requireContext()).dao
        val clientId = args.clientId
        val factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(DetailViewModel::class.java))
                    return DetailViewModel(dao, clientId) as T
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

        binding.speedDial.inflate(R.menu.menu_client)
        binding.speedDial.setOnActionSelectedListener(SpeedDialView.OnActionSelectedListener { actionItem ->
            when (actionItem.id) {
                R.id.action_call -> {
                    viewModel.callAction(binding.speedDial)
                    binding.speedDial.close() // To close the Speed Dial with animation
                    return@OnActionSelectedListener true // false will close it without animation
                }
                R.id.ic_notifications -> {
                    viewModel.notifyAction(binding.speedDial)
                    binding.speedDial.close() // To close the Speed Dial with animation
                    return@OnActionSelectedListener true // false will close it without animation
                }

            }
            false
        })

    }


}



