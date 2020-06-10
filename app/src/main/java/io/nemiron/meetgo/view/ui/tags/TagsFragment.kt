package io.nemiron.meetgo.view.ui.tags

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import io.nemiron.meetgo.R
import io.nemiron.meetgo.databinding.FragmentTagsBinding
import io.nemiron.meetgo.view.ui.main_activity.MainActivity
import io.nemiron.meetgo.view.viewmodels.TagsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class TagsFragment : Fragment(R.layout.fragment_tags) {

    private val binding: FragmentTagsBinding by viewBinding()
    private val viewModel: TagsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() = with(binding) {
        skipTagsButton.setOnClickListener {

        }
    }

    private fun initObservers() = with(viewModel) {
        state.observe(viewLifecycleOwner, Observer { it?.let {

        }})
        errorMessage.observe(viewLifecycleOwner, Observer { it?.let {

        }})
        navigateTo.observe(viewLifecycleOwner, Observer { it?.let {
            navigateToHome()
        }})
    }

    private fun navigateToHome() {
        (activity as MainActivity).setApplicationNavigation()
    }
}
