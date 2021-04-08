package com.cendekia.githubapp.view

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cendekia.githubapp.R
import com.cendekia.githubapp.adapters.ListEventAdapter
import com.cendekia.githubapp.adapters.UserAdapter
import com.cendekia.githubapp.databinding.ActivityMainBinding
import com.cendekia.githubapp.repositories.localdatasource.EventData
import com.cendekia.githubapp.repositories.models.Event
import com.cendekia.githubapp.repositories.models.User
import com.cendekia.githubapp.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var adapter: UserAdapter
    private var listevent: ArrayList<Event> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.github_icon)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setContentView(binding.root)
        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: User) {
                Intent(this@MainActivity, DetailUserActivity::class.java).also {
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailUserActivity.EXTRA_ID, data.id)
                    it.putExtra(DetailUserActivity.EXTRA_AVATAR, data.avatar_url)
                    startActivity(it)
                }
            }
        })

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            MainActivityViewModel::class.java
        )

        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.setHasFixedSize(true)
            rvUser.adapter = adapter

            btnSearch.setOnClickListener {
                searchUser()
            }

            etQuery.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        viewModel.getSearchUsers().observe(this, Observer {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            }
        })

        binding.rvEvents.setHasFixedSize(true)
        listevent.addAll(EventData.listData)
        showRecyclerEventList()
    }

    private fun showRecyclerEventList() {
        binding.rvEvents.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val listEventAdapter = ListEventAdapter(listevent)
        binding.rvEvents.adapter = listEventAdapter

        listEventAdapter.setOnItemClickCallback(object : ListEventAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Event) {
                    showSelectedEvent(data)
            }
        })

    }

    private fun showSelectedEvent(event: Event) {
        val moveDetailEvent = Intent(this@MainActivity, DetailEventActivity::class.java)
        moveDetailEvent.putExtra(DetailEventActivity.EXTRA_EVENT, event)
        startActivity(moveDetailEvent)
    }

    private fun searchUser() {
        binding.apply {
            val query = etQuery.text.toString()
            if (query.isEmpty()) return
            showLoading(true)
            viewModel.setSearchUsers(query)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite_menu -> {
                Intent(this, FavoriteActivity::class.java).also {
                    startActivity(it)
                }
            }
            R.id.setting_menu -> {
                Intent(this, SettingsActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}