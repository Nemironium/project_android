package io.nemiron.meetgo.view.onboarding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import io.nemiron.meetgo.R

class SlideAdapter (private val context: Context, private val slides: List<SlideItem>): PagerAdapter() {

    private lateinit var layoutInflater: LayoutInflater

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.item_on_boarding, container, false) as View

        bind(view, position)
        container.addView(view)
        return view
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount() = slides.size

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }

    private fun bind(view: View, pos: Int) = with(view) {
        findViewById<TextView>(R.id.slide_header).setText(slides[pos].headerResId)
        findViewById<TextView>(R.id.slide_description).setText(slides[pos].descriptionResId)
        findViewById<ImageView>(R.id.slide_image).setImageResource(slides[pos].imageResId)
    }
}