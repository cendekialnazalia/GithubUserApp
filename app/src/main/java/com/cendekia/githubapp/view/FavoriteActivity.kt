package com.cendekia.githubapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cendekia.githubapp.R
import com.cendekia.githubapp.adapters.UserAdapter
import com.cendekia.githubapp.databinding.ActivityFavoriteBinding
import com.cendekia.githubapp.repositories.localdatasource.FavoriteUser
import com.cendekia.githubapp.repositories.models.User
import com.cendekia.githubapp.viewmodels.FavoriteActivityViewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: FavoriteActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.github_icon)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(
            FavoriteActivityViewModel::class.java)

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                Intent(this@FavoriteActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailUserActivity.EXTRA_ID, data.id)
                    it.putExtra(DetailUserActivity.EXTRA_AVATAR, data.avatar_url)
                    startActivity(it)
                }
            }

        })

        showLoading(true)
        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter
        }

        viewModel.getFavoriteUser()?.observe(this, Observer {
            if (it!=null){
                val list = mapList(it)
                adapter.setList(list)
                showLoading(false)
            }
        })
    }

    private fun mapList(users: List<FavoriteUser>?): ArrayList<User> {
        val listUsers = ArrayList<User>()
        if (users != null) {
            for (user in users){
                val userMapped = User(
                    user.login,
                    user.id,
                    user.avatar_url
                )
                listUsers.add(userMapped)
            }
        }
        return listUsers
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}