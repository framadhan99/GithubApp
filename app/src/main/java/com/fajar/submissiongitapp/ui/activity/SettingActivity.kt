package com.fajar.submissiongitapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import by.kirich1409.viewbindingdelegate.viewBinding
import com.fajar.submissiongitapp.R
import com.fajar.submissiongitapp.databinding.ActivitySettingBinding
import com.fajar.submissiongitapp.ui.viewmodel.SettingViewModel

class SettingActivity : AppCompatActivity(R.layout.activity_setting) {
    private val binding by viewBinding(ActivitySettingBinding::bind)
    private val settingsViewModel by viewModels<SettingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.settingsToolbar)

        val pref = settingsViewModel.preferences

        settingsViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.switchDarkMode.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switchDarkMode.isChecked = false
            }
        }


        binding.switchDarkMode.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            settingsViewModel.saveThemeSettings(isChecked)
        }

        binding.settingsToolbar.setNavigationOnClickListener { finish() }

    }
}