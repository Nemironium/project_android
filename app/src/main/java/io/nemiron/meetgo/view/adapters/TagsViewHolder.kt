package io.nemiron.meetgo.view.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import io.nemiron.meetgo.view.items.TagItem
import kotlinx.android.synthetic.main.item_tags.view.*

class TagsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun populateTags(tag: TagItem) {
        itemView.tag_name.text = tag.name
        itemView.tag_card.isChecked = tag.isChecked
    }

}