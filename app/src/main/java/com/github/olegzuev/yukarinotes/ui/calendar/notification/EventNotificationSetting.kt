package com.github.olegzuev.yukarinotes.ui.calendar.notification

import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.Preference.OnPreferenceChangeListener
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.github.olegzuev.yukarinotes.R
import com.github.olegzuev.yukarinotes.common.NotificationManager
import com.github.olegzuev.yukarinotes.common.TYPE_STRING_LIST

class EventNotificationSetting : PreferenceFragmentCompat(){

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.event_notification_preferences, rootKey)

        TYPE_STRING_LIST.forEach {
            findPreference<SwitchPreferenceCompat>(it)?.onPreferenceChangeListener = OnPreferenceChangeListener(switchNotification())
        }
    }

    private fun switchNotification(): (p: Preference, b: Any) -> Boolean {
        return { p , b ->
            NotificationManager.get().refreshSpecificNotification(p.key, b as Boolean)
            true
        }
    }
}