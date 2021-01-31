package com.oladipo.fairmoneytest.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.oladipo.fairmoneytest.R
import com.oladipo.fairmoneytest.Utils
import com.oladipo.fairmoneytest.adapter.UserAdapter
import com.oladipo.fairmoneytest.databinding.FragmentMainBinding
import com.oladipo.fairmoneytest.viemodel.MainViewModel

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this,MainViewModel.Factory(activity.application)).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel
        val adapter = UserAdapter(UserAdapter.UserListener{
            viewModel.onUserClicked(it)
        })
        val manager = GridLayoutManager(context, 2)
        binding.usersRecycler.layoutManager = manager
        binding.usersRecycler.adapter = adapter

        viewModel.status.observe(viewLifecycleOwner, Observer {
            it?.let {
                when(it){
                    MainViewModel.DummyAPIStatus.LOADING->
                        binding.statusLoadingWheel.visibility = View.VISIBLE
                    MainViewModel.DummyAPIStatus.DONE ->
                        binding.statusLoadingWheel.visibility = View.GONE
                    MainViewModel.DummyAPIStatus.ERROR ->
                        Utils.useSnackBar(requireActivity().findViewById(android.R.id.content),
                            getString(R.string.loading_error_message))
                }
            }
        })

        viewModel.users.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToDetails.observe(viewLifecycleOwner, Observer {
            it?.let {
                this.findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(it))
                viewModel.onUserCompleteNavigation()
            }
        })

        return binding.root

    }


}