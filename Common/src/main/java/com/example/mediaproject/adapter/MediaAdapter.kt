package com.example.mediaproject.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mediaproject.model.MediaItem
import com.example.mediaproject.databinding.ItemMediaBinding
import com.example.mediaproject.utilities.Constants

class MediaAdapter(
    var items: MutableList<MediaItem> = mutableListOf(
        MediaItem.Builder()
            .title("No Data Yet..")
            .genre("")
            .build()
    ),
    private val onItemChanged: () -> Unit
) : RecyclerView.Adapter<MediaAdapter.MediaViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MediaViewHolder {
        val binding = ItemMediaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return MediaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        val item = items[position]

        holder.binding.itemLBLTitle.text = item.title
        holder.binding.itemLBLGenre.text = item.genre
        holder.binding.itemCHIPStatus.text = item.status.name

        holder.binding.itemSWTWatched.setOnCheckedChangeListener(null)

        holder.binding.itemSWTWatched.isChecked =
            item.status == Constants.WatchStatus.WATCHED

        holder.binding.itemSWTWatched.setOnCheckedChangeListener { _, isChecked ->
            item.status = if (isChecked) {
                Constants.WatchStatus.WATCHED
            } else {
                Constants.WatchStatus.NOT_WATCHED
            }

            holder.binding.itemCHIPStatus.text = item.status.name
            onItemChanged()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateItems(newItems: MutableList<MediaItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class MediaViewHolder(val binding: ItemMediaBinding) :
        RecyclerView.ViewHolder(binding.root)
}