package io.nemiron.meetgo.view.on_boarding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import by.kirich1409.viewbindingdelegate.viewBinding
import io.nemiron.meetgo.R
import io.nemiron.meetgo.core.helpers.AppPrefs
import io.nemiron.meetgo.core.helpers.hide
import io.nemiron.meetgo.core.helpers.show
import io.nemiron.meetgo.databinding.FragmentOnBoardingBinding
import org.koin.android.ext.android.inject

class OnBoardingFragment : Fragment(R.layout.fragment_on_boarding) {

    private val boardingBinding: FragmentOnBoardingBinding by viewBinding()
    private val appPrefs: AppPrefs by inject()
    private val slides = Slides.slides

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
        initListeners()
    }

    private fun initViewPager() = with(boardingBinding) {
        slider.adapter = SlideAdapter(this@OnBoardingFragment.requireContext(), slides)
        dotsIndicator.setViewPager(boardingBinding.slider)
    }

    private fun initListeners() = with(boardingBinding) {
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
        boardingBinding.nextButton.text = getText(R.string.start)
    }

    private fun setNextButton() {
        boardingBinding.nextButton.text = getText(R.string.next)
    }

    private fun goToNextSlide() {
        val currentItem = boardingBinding.slider.currentItem + 1

        if (currentItem < slides.size)
            boardingBinding.slider.currentItem = currentItem
        else
            navigateToLogin()
    }

    private fun navigateToLogin() {
        appPrefs.isFirstTimeLaunch = false
        findNavController().navigate(R.id.action_onBoarding_to_login)
    }
}
