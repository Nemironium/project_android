package io.nemiron.meetgo.view.on_boarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import io.nemiron.meetgo.view.login.LoginActivity
import io.nemiron.meetgo.R
import io.nemiron.meetgo.databinding.ActivityOnBoardingBinding
import io.nemiron.meetgo.core.helpers.AppPrefs
import org.koin.android.ext.android.inject


class OnBoardingActivity : AppCompatActivity() {

    private val appPrefs: AppPrefs by inject()

    private val slides = listOf(
        SlideItem(
            R.string.slide_header_1,
            R.string.slide_description_1,
            R.drawable.play
        ),
        SlideItem(
            R.string.slide_header_2,
            R.string.slide_description_2,
            R.drawable.play_anywhere
        ),
        SlideItem(
            R.string.slide_header_3,
            R.string.slide_description_3,
            R.drawable.play_together
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewPager(binding)
        initListeners(binding)
    }

    private fun initViewPager(binding: ActivityOnBoardingBinding) = with(binding) {
        slider.adapter = SlideAdapter(
            this@OnBoardingActivity,
            slides
        )
        dotsIndicator.setViewPager(binding.slider)
    }

    private fun initListeners(binding: ActivityOnBoardingBinding) {
        with(binding) {
            slider.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageSelected(position: Int) {
                    if (position == slides.size - 1)  {
                        nextButton.hide()
                        skipButton.hide()
                        startButton.show()
                    } else {
                        nextButton.show()
                        skipButton.show()
                        startButton.hide()
                    }
                }
                override fun onPageScrollStateChanged(state: Int) {}
                override fun onPageScrolled(position: Int, posOffset: Float, posOffsetPixels: Int) {}
            })

            startButton.setOnClickListener { goToLogin() }
            skipButton.setOnClickListener { goToLogin() }
            nextButton.setOnClickListener {
                val currentItem = slider.currentItem + 1
                if (currentItem < slides.size)
                    slider.currentItem = currentItem
            }
        }
    }

    private fun goToLogin() {
        appPrefs.isFirstTimeLaunch = false
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
