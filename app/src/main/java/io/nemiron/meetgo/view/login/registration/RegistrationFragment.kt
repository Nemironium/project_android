package io.nemiron.meetgo.view.login.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.nemiron.meetgo.R
import io.nemiron.meetgo.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {

    private lateinit var registrationBinding: FragmentRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        registrationBinding = FragmentRegistrationBinding.inflate(inflater, container, false)

        registrationBinding.toInviteUser.setOnClickListener {
            findNavController().navigate(R.id.action_registration_to_changePartner)
        }

        // Inflate the layout for this fragment
        return registrationBinding.root
    }
}
