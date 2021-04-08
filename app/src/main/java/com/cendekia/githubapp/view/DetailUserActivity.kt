package com.cendekia.githubapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.cendekia.githubapp.R
import com.cendekia.githubapp.adapters.SectionPagerAdapter
import com.cendekia.githubapp.databinding.ActivityDetailUserBinding
import com.cendekia.githubapp.viewmodels.DetailUserActivityViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserActivityViewModel

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_AVATAR = "extra_avatar"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.github_icon)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val avatarUrl = intent.getStringExtra(EXTRA_AVATAR)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        val sectionsPagerAdapter = SectionPagerAdapter(this, bundle)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f

        viewModel = ViewModelProvider(this).get(
            DetailUserActivityViewModel::class.java
        )

        username?.let { showLoading(true)
            viewModel.setUserDetail(it) }
        viewModel.getUserDetail().observe(this, Observer {
            if (it != null) {
                binding.apply {
                    tvDetailUsername.text = it.login
                    tvDetailFollower.text = it.followers.toString()
                    tvDetailFollowing.text = it.following.toString()
                    tvDetailRepository.text = it.public_repos.toString()
                    tvDetailName.text = it.name
                    tvDetailId.text = it.id.toString()
                    tvDetailLocation.text = it.location
                    tvDetailCompany.text = it.company
                    tvDetailBlog.text = it.blog
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .centerCrop()
                        .into(ivDetailImgGithub)
                    showLoading(false)
                }
            }
        })

        var isChacked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = viewModel.checkUser(id)
            withContext(Dispatchers.Main) {
                if (count != null) {
                    if (count > 0) {
                        binding.tbFavorite.isChecked = true
                        isChacked = true
                    } else {
                        binding.tbFavorite.isChecked = false
                        isChacked = false
                    }
                }
            }
        }

        binding.tbFavorite.setOnClickListener {
            isChacked = !isChacked
            if (isChacked) {
                if (username != null) {
                    if (avatarUrl != null) {
                        viewModel.addToFavorite(username, id, avatarUrl)
                    }
                }
            } else {
                viewModel.removeFromFavorite(id)
            }
            binding.tbFavorite.isChecked = isChacked
        }

    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}