package com.soopeach.thethethe_android.recommendation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.soopeach.thethethe_android.databinding.FragmentRecommendationBinding
import com.soopeach.thethethe_android.model.RecommendationVideo

class RecommendationFragment : Fragment() {

    private var _binding: FragmentRecommendationBinding? = null
    private val binding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecommendationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = RecommendationVideoListAdapter { videoId ->
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://www.youtube.com/watch?v=$videoId")
                setPackage("com.google.android.youtube")
            }

            startActivity(intent)
        }

        binding.recommendationVideoRecyclerView.adapter = adapter
        binding.recommendationVideoRecyclerView.layoutManager =
            LinearLayoutManager(requireContext())

        adapter.submitList(
            listOf(
                RecommendationVideo(
                    "-KLXcO_BZ8A",
                    "거북목 교정 운동, 이거 하나로 끝낸다! - 치맥 운동",
                    "https://i.ytimg.com/vi/-KLXcO_BZ8A/mqdefault.jpg"
                ),
                RecommendationVideo(
                    "0D6fn3i2-e0",
                    "거북목-일자목-뒷목-어깨 통증 간단하게 해결하는 방법",
                    "https://i.ytimg.com/vi/0D6fn3i2-e0/mqdefault.jpg"
                ),
                RecommendationVideo(
                    "1g6vB2O4qZQ",
                    "[Ep.09] 잘 낫지 않는 거북목 \uD83D\uDE31 올바른 스트레칭으로 교정하세요! - 안좋은 자세, 습관, 직업 등으로 형성된 거북목은 만성적인 두통, 목통증의 원인이 됩니다!",
                    "https://i.ytimg.com/vi/1g6vB2O4qZQ/mqdefault.jpg"
                )
            )
        )

    }


}