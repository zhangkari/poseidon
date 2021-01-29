package com.class100.yunshixun

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.class100.atropos.env.context.AtPrefs
import com.class100.oceanides.OcActivity
import com.class100.poseidon.PsWebActivity
import kotlinx.android.synthetic.main.activity_devops.*

class DevOpsActivity : OcActivity() {
    companion object {
        private val HISTORY_URL = mutableListOf<String>()
        private const val DEV_OPS_URL_HISTORY = "dev_ops_url_history";

        fun launch(context: Context) {
            context.startActivity(Intent(context, DevOpsActivity::class.java))
        }
    }

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        setContentView(R.layout.activity_devops)
        init()
    }

    private fun init() {
        val data = AtPrefs.get(DEV_OPS_URL_HISTORY, "");
        et_url.setText(data)
        btn_go.setOnClickListener {
            PsWebActivity.launch(it.context, et_url.text.toString())
            AtPrefs.put(DEV_OPS_URL_HISTORY, et_url.text.toString())
        }
    }

}