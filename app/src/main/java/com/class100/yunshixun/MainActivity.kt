package com.class100.yunshixun

import android.view.View
import com.class100.atropos.generic.AtFreqClick
import com.class100.oceanides.OcActivity

class MainActivity : OcActivity() {
    private val multiClick = lazy {
        AtFreqClick(5, 1000)
    }

    override fun getContentLayout(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        super.init()
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