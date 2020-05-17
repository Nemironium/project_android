package io.nemiron.meetgo.view.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import io.nemiron.meetgo.R
import io.nemiron.meetgo.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var profileViewModel: ProfileViewModel
    private val binding: FragmentProfileBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileViewModel =
            ViewModelProviders.of(this).get(ProfileViewModel::class.java)

        profileViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textProfile.text = it
        })

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_settings)
        }

    }
}
