package com.soopeach.thethethe_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.soopeach.thethethe_android.databinding.ActivityMainBinding
import com.soopeach.thethethe_android.rank.RankFragment
import com.soopeach.thethethe_android.recommendation.RecommendationFragment

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Home"

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->

            when(item.itemId) {
                R.id.nav1 -> {
                    loadFragment(PopFragment())
                    supportActionBar?.title = "Home"
                }
                R.id.nav2 -> {
                    loadFragment(RankFragment())
                    supportActionBar?.title="Ranking"
                }
                R.id.nav3 -> {
                    loadFragment(RecommendationFragment())
                    supportActionBar?.title="Youtube"
                }
            }
            true
        }
        loadFragment(PopFragment())
        bottomNavigationView.selectedItemId=R.id.nav1
    }

    override fun onStart() {
        super.onStart()

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->

            when(item.itemId) {
                R.id.nav1 -> {
                    loadFragment(PopFragment())
                    supportActionBar?.title = "Home"
                }
                R.id.nav2 -> {
                    loadFragment(RankFragment())
                    supportActionBar?.title="Ranking"
                }
                R.id.nav3 -> {
                    loadFragment(RecommendationFragment())
                    supportActionBar?.title="Youtube"
                }
            }
            true
        }
    }


    fun loadFragment(fragment: Fragment, message: Int = 0) {
        val bundle = Bundle()
        bundle.putInt("message", message)
        fragment.arguments = bundle
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_layout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}