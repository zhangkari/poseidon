package com.class100.yunshixun

import android.os.Bundle
import android.view.View
import com.class100.atropos.generic.AtFreqClick
import com.class100.oceanides.OcActivity

class MainActivity : OcActivity() {
    private val multiClick = lazy {
        AtFreqClick(5, 1000)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListener()
    }

    private fun setListener() {
        findViewById<View>(android.R.id.content).setOnClickListener { _ ->
            multiClick.value.onClick {
                DevOpsActivity.launch(this)
            }
        }
    }
}