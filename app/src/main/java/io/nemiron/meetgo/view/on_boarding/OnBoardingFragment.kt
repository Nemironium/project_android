package io.nemiron.meetgo.view.on_boarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import io.nemiron.meetgo.R
import io.nemiron.meetgo.core.helpers.AppPrefs
import io.nemiron.meetgo.core.helpers.hide
import io.nemiron.meetgo.core.helpers.show
import io.nemiron.meetgo.databinding.FragmentOnBoardingBinding
import org.koin.android.ext.android.inject

class OnBoardingFragment : Fragment() {

    private lateinit var boardingBinding: FragmentOnBoardingBinding
    private val appPrefs: AppPrefs by inject()
    private val slides = listOf(
        SlideItem(R.string.slide_header_1, R.string.slide_description_1, R.drawable.slide_do_tasks),
        SlideItem(R.string.slide_header_2, R.string.slide_description_2, R.drawable.slide_play_partner),
        SlideItem(R.string.slide_header_3, R.string.slide_description_3, R.drawable.slide_play_anywhere)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        boardingBinding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        initViewPager()
        initListeners()

        return boardingBinding.root
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
