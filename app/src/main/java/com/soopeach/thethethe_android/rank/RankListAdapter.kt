package com.soopeach.thethethe_android.rank

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.soopeach.thethethe_android.R
import com.soopeach.thethethe_android.databinding.RankItemBinding
import com.soopeach.thethethe_android.model.couple.Couple
import com.soopeach.thethethe_android.model.couple.CoupleResponse
import com.soopeach.thethethe_android.utils.toProcessedString

class RankListAdapter() : ListAdapter<CoupleResponse, RankViewHolder>(
    diffCallback
) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<CoupleResponse>() {
            override fun areItemsTheSame(
                oldItem: CoupleResponse,
                newItem: CoupleResponse
            ) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: CoupleResponse,
                newItem: CoupleResponse
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
    fun bind(item: CoupleResponse, seq: Int) {
        with(binding) {

            rank.text = seq.toString()
            coupleName.text = item.coupleName
            introduction.text = item.introduction
            score.text = item.coupleTotalScore.toProcessedString()
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

                else -> {
                    this.root.setBackgroundResource(R.drawable.bg_rounded_border_16_grey)
                }
            }
        }
    }
}