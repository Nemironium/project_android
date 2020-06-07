package io.nemiron.meetgo.view.ui.tags

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import io.nemiron.meetgo.R
import io.nemiron.meetgo.databinding.FragmentTagsBinding
import io.nemiron.meetgo.view.ui.main_activity.MainActivity


class TagsFragment : Fragment(R.layout.fragment_tags) {

    private val binding: FragmentTagsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding) {

    }

    private fun navigateToHome() {
        (activity as MainActivity).setApplicationNavigation()
    }
}
