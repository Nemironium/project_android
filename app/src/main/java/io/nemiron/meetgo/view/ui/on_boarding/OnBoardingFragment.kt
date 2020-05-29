package io.nemiron.meetgo.view.ui.on_boarding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import by.kirich1409.viewbindingdelegate.viewBinding
import io.nemiron.meetgo.R
import io.nemiron.meetgo.data.network.AuthorizationHelper
import io.nemiron.meetgo.databinding.FragmentOnBoardingBinding
import io.nemiron.meetgo.extensions.hide
import io.nemiron.meetgo.extensions.show
import io.nemiron.meetgo.view.adapters.SlideAdapter


class OnBoardingFragment(private val authorizationHelper: AuthorizationHelper) : Fragment(R.layout.fragment_on_boarding) {

    private val binding: FragmentOnBoardingBinding by viewBinding()
    private val slides = Slides.slides

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        initListeners()
    }

    private fun initViewPager() = with(binding) {
        slider.adapter = SlideAdapter(
            this@OnBoardingFragment.requireContext(),
            slides
        )
        dotsIndicator.setViewPager(binding.slider)
    }

    private fun initListeners() = with(binding) {
        slider.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                if (position == slides.lastIndex)  {
                    setStartButton()
                    skipButton.hide()
                } else {
                    setNextButton()
                    skipButton.show()
                }
            }
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(position: Int, posOffset: Float, posOffsetPixels: Int) {}
        })

        skipButton.setOnClickListener { navigateToLogin() }
        nextButton.setOnClickListener { goToNextSlide() }
    }

    private fun setStartButton() {
        binding.nextButton.text = getText(R.string.start)
    }

    private fun setNextButton() {
        binding.nextButton.text = getText(R.string.next)
    }

    private fun goToNextSlide() {
        val currentItem = binding.slider.currentItem + 1

        if (currentItem < slides.size)
            binding.slider.currentItem = currentItem
        else
            navigateToLogin()
    }

    private fun navigateToLogin() {
        authorizationHelper.isFirstTimeLaunch = false
        findNavController().navigate(R.id.action_onBoarding_to_login)
    }
}
