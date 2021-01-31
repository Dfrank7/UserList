package com.oladipo.fairmoneytest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.oladipo.fairmoneytest.databinding.FragmentDetailBinding

class DetailFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        return binding.root
    }
}