package io.nemiron.meetgo.view.change_partner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.nemiron.meetgo.MainActivity
import io.nemiron.meetgo.R
import io.nemiron.meetgo.databinding.FragmentChangePartnerBinding

class ChangePartnerFragment : Fragment() {

    private lateinit var binding: FragmentChangePartnerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentChangePartnerBinding.inflate(inflater, container, false)
        binding.inviteUser.setOnClickListener {
            navigateToHome()
        }
        return binding.root
    }

    private fun navigateToHome() {
        findNavController().navigate(R.id.action_changePartner_to_home)
       (activity as MainActivity).setupBottomNavigationBar()
    }


}
