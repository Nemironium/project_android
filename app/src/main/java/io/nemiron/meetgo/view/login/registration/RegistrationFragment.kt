package io.nemiron.meetgo.view.login.registration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import io.nemiron.meetgo.R
import io.nemiron.meetgo.core.helpers.AuthorizationHelper
import io.nemiron.meetgo.databinding.FragmentRegistrationBinding

class RegistrationFragment(private val authorizationHelper: AuthorizationHelper) : Fragment(R.layout.fragment_registration) {

    private val binding: FragmentRegistrationBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener {
            //authorizationHelper.isLogged = true
            findNavController().navigate(R.id.action_registration_to_changePartner)
        }
    }
}
