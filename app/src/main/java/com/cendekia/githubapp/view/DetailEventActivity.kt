package com.cendekia.githubapp.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cendekia.githubapp.R
import com.cendekia.githubapp.databinding.ActivityDetailEventBinding
import com.cendekia.githubapp.repositories.models.Event

class DetailEventActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailEventBinding

    companion object {
        const val EXTRA_EVENT = "extra_event"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.github_icon)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val event = intent.getParcelableExtra<Event>(EXTRA_EVENT) as Event

        binding.ivDetailImgevent.setImageResource(event.photo_events)
        binding.tvDetailHeadevent.text = event.headline_detail_events
        binding.tvDetailAuthor.text = event.author_detail_events
        binding.tvDetailDate.text = event.date_detail_event
        binding.tvDetailContent.text = event.content_detail_event


    }
}