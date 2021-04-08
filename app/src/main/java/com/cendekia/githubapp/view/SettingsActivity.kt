package com.cendekia.githubapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import com.cendekia.githubapp.R
import com.cendekia.githubapp.databinding.ActivitySettingsBinding
import com.cendekia.githubapp.preference.RemainderPreference
import com.cendekia.githubapp.receiver.AlarmReceiver
import com.cendekia.githubapp.repositories.models.Remainder

class SettingsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var remainder: Remainder
    private lateinit var alarmReceiver: AlarmReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.github_icon)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val remainderPreference = RemainderPreference(this)
        binding.switch1.isChecked = remainderPreference.getRemainder().isRemainded

        alarmReceiver = AlarmReceiver()

        binding.switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                saveRemainder(true)
                alarmReceiver.setRepeatingAlarm(
                    this, getString(R.string.type_set_repeating_alarm), getString(
                        R.string.time_alarm
                    ), getString(R.string.massage_alarm)
                )
            } else {
                saveRemainder(false)
                alarmReceiver.cancelAlarm(this)
            }
        }

        binding.btnSetLanguage.setOnClickListener(this)
    }

    private fun saveRemainder(state: Boolean) {
        val remainderPreference = RemainderPreference(this)
        remainder = Remainder()

        remainder.isRemainded = state
        remainderPreference.setRemainder(remainder)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_set_language -> {
                val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
            }
        }
    }
}