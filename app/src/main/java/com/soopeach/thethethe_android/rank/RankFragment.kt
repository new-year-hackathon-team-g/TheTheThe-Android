package com.soopeach.thethethe_android.rank

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.soopeach.thethethe_android.databinding.FragmentRankBinding
import com.soopeach.thethethe_android.databinding.FragmentRecommendationBinding
import com.soopeach.thethethe_android.model.Couple

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

        val adapter = RankListAdapter()
        binding.RecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.RecyclerView.adapter = adapter

        adapter.submitList(
            listOf(
                Couple(
                    1,
                    "꽁냥커플",
                    "https://source.unsplash.com/user/max_duz/300x300",
                    "안녕하세요",
                    "2021-09-01"
                ),
                Couple(
                    2,
                    "카풀할 사람",
                    "https://source.unsplash.com/user/max_duz/300x300",
                    "님 카풀 해본 적 있나요",
                    "2021-09-01"
                ),
                Couple(
                    3,
                    "아 배고파",
                    "https://source.unsplash.com/user/max_duz/300x300",
                    "한줄 소개",
                    "2021-09-01"
                ),
                Couple(
                    4,
                    "커플 커플",
                    "https://source.unsplash.com/user/max_duz/300x300",
                    "안녕하세요",
                    "2021-09-01"
                ),
                Couple(
                    5,
                    "잉꼬부부",
                    "https://source.unsplash.com/user/max_duz/300x300",
                    "안녕하세요",
                    "2021-09-01"
                ),
                Couple(
                    6,
                    "원앙 커플",
                    "https://source.unsplash.com/user/max_duz/300x300",
                    "으아아아아아ㅏ아아ㅏㅏㅏㅏㅏㅏㅏㅏㅏ",
                    "2021-09-01"
                ),
            )
        )

    }

}