package com.class100.yunshixun

import android.app.Application
import com.class100.atropos.AtAbilityManager
import com.class100.poseidon.PsWebActivity

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AtAbilityManager.initialize(this, BuildConfig.DEBUG)
        PsWebActivity.initialize(this)
    }
}