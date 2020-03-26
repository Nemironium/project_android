package io.nemiron.meetgo.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.nemiron.meetgo.core.helpers.AppPrefs
import io.nemiron.meetgo.databinding.FragmentLoginBinding
import org.koin.android.ext.android.inject


class LoginFragment : Fragment() {

    private lateinit var loginBinding: FragmentLoginBinding
    private val appPrefs: AppPrefs by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (appPrefs.isFirstTimeLaunch) {
            // navigate to OnBoardingFragment
        } else if (appPrefs.isLogged) {
            // navigate to HomeFragment
        }

        loginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        initViews()

        return loginBinding.root
    }

    private fun initViews() {
        loginBinding.forgotPassword.setOnClickListener {
            // TODO(navigate to ForgotPasswordFragment)
        }

        loginBinding.forgotPassword.setOnClickListener {
            // TODO(navigate to Register)
        }
    }



}
