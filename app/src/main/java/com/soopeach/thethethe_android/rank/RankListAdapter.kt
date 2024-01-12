package com.soopeach.thethethe_android.rank

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.soopeach.thethethe_android.R
import com.soopeach.thethethe_android.databinding.RankItemBinding
import com.soopeach.thethethe_android.model.Couple
import com.soopeach.thethethe_android.utils.toProcessedString

class RankListAdapter() : ListAdapter<Couple, RankViewHolder>(
    diffCallback
) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Couple>() {
            override fun areItemsTheSame(
                oldItem: Couple,
                newItem: Couple
            ) =
                oldItem.cid == newItem.cid

            override fun areContentsTheSame(
                oldItem: Couple,
                newItem: Couple
            ) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RankViewHolder {
        return RankViewHolder(
            RankItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RankViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, position + 1)
        }
    }

}

class RankViewHolder(private val binding: RankItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Couple, seq: Int) {
        with(binding) {

            rank.text = seq.toString()
            coupleName.text = item.coupleName
            introduction.text = item.introduction
            score.text = item.store.toProcessedString()
            Glide.with(root)
                .load(item.coupleImageUrl)
                .into(profileImg)

            when (seq) {
                1 -> {
                    this.root.setBackgroundResource(R.drawable.bg_rounded_border_16_gold)
                }

                2 -> {
                    this.root.setBackgroundResource(R.drawable.bg_rounded_border_16_silver)
                }

                3 -> {
                    this.root.setBackgroundResource(R.drawable.bg_rounded_border_16_bronze)
                }
            }
        }
    }
}