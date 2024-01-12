package com.soopeach.thethethe_android.rank

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.soopeach.thethethe_android.R
import com.soopeach.thethethe_android.databinding.FragmentRankBinding
import com.soopeach.thethethe_android.databinding.FragmentRecommendationBinding
import com.soopeach.thethethe_android.model.Couple
import com.soopeach.thethethe_android.utils.toProcessedString

class RankFragment : Fragment() {

    private var _binding: FragmentRankBinding? = null
    private val binding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRankBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myCoupleData = Couple(
            123,
            "푸하하",
            "https://source.unsplash.com/user/max_duz/300x300",
            "우리가 최고",
            "2021-09-01",
            10000000)

        val otherCoupleData = listOf(
            Couple(
                123,
                "푸하하",
                "https://source.unsplash.com/user/max_duz/300x300",
                "우리가 최고",
                "2021-09-01",
                10000000
            ),
            Couple(
                1,
                "꽁냥커플",
                "https://source.unsplash.com/user/max_duz/300x300",
                "안녕하세요",
                "2021-09-01",
                100000
            ),
            Couple(
                2,
                "카풀할 사람",
                "https://source.unsplash.com/user/max_duz/300x300",
                "님 카풀 해본 적 있나요",
                "2021-09-01",
                20300
            ),
            Couple(
                3,
                "아 배고파",
                "https://source.unsplash.com/user/max_duz/300x300",
                "한줄 소개",
                "2021-09-01",
                232
            ),
            Couple(
                4,
                "커플 커플",
                "https://source.unsplash.com/user/max_duz/300x300",
                "안녕하세요",
                "2021-09-01", 12
            ),
            Couple(
                5,
                "잉꼬부부",
                "https://source.unsplash.com/user/max_duz/300x300",
                "안녕하세요",
                "2021-09-01",
                1
            ),
            Couple(
                6,
                "원앙 커플",
                "https://source.unsplash.com/user/max_duz/300x300",
                "으아아아아아ㅏ아아ㅏㅏㅏㅏㅏㅏㅏㅏㅏ",
                "2021-09-01",
                0
            ),
        )

        with(binding.myCoupleRank) {

            val myRank = otherCoupleData.indexOfFirst { it.cid == myCoupleData.cid } + 1
            rank.text = myRank.toString()
            coupleName.text = myCoupleData.coupleName
            introduction.text = myCoupleData.introduction
            score.text = myCoupleData.store.toProcessedString()
            Glide.with(root)
                .load(myCoupleData.coupleImageUrl)
                .into(profileImg)

            when (myRank) {
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


        val adapter = RankListAdapter()
        binding.RecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.RecyclerView.adapter = adapter

        adapter.submitList(
            otherCoupleData
        )

    }

}