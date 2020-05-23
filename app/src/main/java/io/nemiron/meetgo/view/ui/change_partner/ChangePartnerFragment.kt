package io.nemiron.meetgo.view.ui.change_partner

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import io.nemiron.meetgo.view.ui.main_activity.MainActivity
import io.nemiron.meetgo.R
import io.nemiron.meetgo.databinding.FragmentChangePartnerBinding


class ChangePartnerFragment : Fragment(R.layout.fragment_change_partner) {

    private val binding: FragmentChangePartnerBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() = with(binding) {
        inviteUser.setOnClickListener { navigateToHome() }
    }

    private fun navigateToHome() {
        (activity as MainActivity).setApplicationNavigation()
    }
}
