package com.soopeach.thethethe_android.recommendation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.soopeach.thethethe_android.databinding.ItemRecommendationVideoBinding
import com.soopeach.thethethe_android.model.RecommendationVideo

class RecommendationVideoListAdapter(
    private val onItemClicked: (String) -> Unit
) :
    ListAdapter<RecommendationVideo, RecommendationVideoViewHolder>(
        diffCallback
    ) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<RecommendationVideo>() {
            override fun areItemsTheSame(
                oldItem: RecommendationVideo,
                newItem: RecommendationVideo
            ) =
                oldItem.videoId == newItem.videoId

            override fun areContentsTheSame(
                oldItem: RecommendationVideo,
                newItem: RecommendationVideo
            ) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendationVideoViewHolder {
        return RecommendationVideoViewHolder(
            ItemRecommendationVideoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecommendationVideoViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, onItemClicked)
        }
    }

}

class RecommendationVideoViewHolder(private val binding: ItemRecommendationVideoBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: RecommendationVideo, onItemClicked: (String) -> Unit) {
        with(binding) {
            Glide.with(root)
                .load(item.thumbnailUrl)
                .into(videoThumbnailImg)

            title.text = item.title
            binding.root.setOnClickListener {
                onItemClicked(item.videoId)
            }
        }
    }
}