package io.nemiron.meetgo.view.login.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.nemiron.meetgo.R
import io.nemiron.meetgo.core.helpers.AppPrefs
import io.nemiron.meetgo.databinding.FragmentRegistrationBinding
import org.koin.android.ext.android.inject

class RegistrationFragment : Fragment() {

    private lateinit var registrationBinding: FragmentRegistrationBinding
    private val appPrefs: AppPrefs by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        registrationBinding = FragmentRegistrationBinding.inflate(inflater, container, false)

        registrationBinding.toInviteUser.setOnClickListener {
            appPrefs.isLogged = true
            findNavController().navigate(R.id.action_registration_to_changePartner)
        }

        return registrationBinding.root
    }
}
