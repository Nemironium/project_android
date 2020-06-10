package io.nemiron.meetgo.view.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import io.nemiron.meetgo.R
import io.nemiron.meetgo.extensions.inflate
import io.nemiron.meetgo.view.items.TagItem

class TagsAdapter(

) : RecyclerView.Adapter<TagsViewHolder>() {

    private lateinit var tags: List<TagItem>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {
        val view = parent.inflate(R.layout.item_tags)
        val holder = TagsViewHolder(view)

        return holder

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        holder.apply {

        }
    }

    private fun setTagClickListener(holder: TagsViewHolder) {
        (holder.itemView as MaterialCardView).setOnCheckedChangeListener { card, isChecked ->

        }
    }

}
