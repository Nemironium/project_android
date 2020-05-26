package io.nemiron.meetgo.view.ui.on_boarding

import io.nemiron.meetgo.R
import io.nemiron.meetgo.view.items.SlideItem

object Slides {
    val slides = listOf(
        SlideItem(
            R.string.slide_header_1,
            R.string.slide_description_1,
            R.drawable.slide_do_tasks
        ),
        SlideItem(
            R.string.slide_header_2,
            R.string.slide_description_2,
            R.drawable.slide_play_partner
        ),
        SlideItem(
            R.string.slide_header_3,
            R.string.slide_description_3,
            R.drawable.slide_play_anywhere
        )
    )
}