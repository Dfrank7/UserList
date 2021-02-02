package com.oladipo.fairmoneytest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.oladipo.fairmoneytest.helper.Constants
import com.oladipo.fairmoneytest.R
import com.oladipo.fairmoneytest.helper.Utils
import com.oladipo.fairmoneytest.databinding.FragmentDetailBinding
import com.oladipo.fairmoneytest.model.Detail
import com.oladipo.fairmoneytest.model.Location
import com.oladipo.fairmoneytest.viemodel.DetailViewModel
import com.squareup.picasso.Picasso

class DetailFragment: Fragment() {

    private val viewModel: DetailViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, DetailViewModel.Factory(activity.application)).get(DetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.statusLoading.visibility = View.VISIBLE

        val user = DetailFragmentArgs.fromBundle(requireArguments()).selectedUser
        val id = user.id

            viewModel.getUserDetailsFromRemote(Constants.apikey, id)
            viewModel.getUserDetailFromLocal(id).observe(viewLifecycleOwner, Observer {
                if (it!=null) {
                    it?.let {
                        binding.statusLoading.visibility = View.GONE
                        val detail = Detail(id = it.id, lastName = it.lastName, phone = it.phone, dateOfBirth = it.dateOfBirth, email = it.email,
                                locations = it.location, location = Gson().fromJson(it.location, Location::class.java), firstName = it.firstName,
                                gender = it.gender, picture = it.picture, registerDate = it.registerDate, title = it.title)
                        binding.detailLayout.visibility = View.VISIBLE
                        binding.user = detail
                        Picasso.with(this.context).load(it.picture).into(binding.userImage)
                    }
                }
                if (it == null && !Utils.isConnectionAvailable(requireContext())){
                    binding.statusLoading.visibility = View.GONE
                    Utils.toastMessage(requireContext(), getString(R.string.no_user_detail_error))
                }
            })

        return binding.root
    }


}