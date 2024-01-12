package com.soopeach.thethethe_android.rank

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.soopeach.thethethe_android.R
import com.soopeach.thethethe_android.data.local.AccountDataStore
import com.soopeach.thethethe_android.data.network.NetworkModule
import com.soopeach.thethethe_android.databinding.FragmentRankBinding
import com.soopeach.thethethe_android.model.couple.Couple
import com.soopeach.thethethe_android.utils.toProcessedString
import com.soopeach.thethethe_android.utils.toToken
import kotlinx.coroutines.launch

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

        lifecycleScope.launch {
            val token = AccountDataStore(requireContext()).getAccessToken()!!
            val myCoupleData = NetworkModule.getCoupleInfo(token.toToken())
            val otherCoupleData = NetworkModule.getRanking(token.toToken())
            binding.myShimmer.isVisible = false
            binding.otherShimmer.isVisible = false

            with(binding.myCoupleRank) {

                val myRank = otherCoupleData.indexOfFirst { it.id == myCoupleData.id } + 1
                rank.text = myRank.toString()
                coupleName.text = myCoupleData.coupleName
                introduction.text = myCoupleData.introduction
                score.text = myCoupleData.coupleTotalScore.toProcessedString()
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

                    else -> {
                        this.root.setBackgroundResource(R.drawable.bg_rounded_border_16_grey)
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

}