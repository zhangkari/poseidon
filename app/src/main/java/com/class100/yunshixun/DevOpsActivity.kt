package com.class100.yunshixun

import android.content.Context
import android.content.Intent
import com.class100.atropos.env.context.AtPrefs
import com.class100.oceanides.OcActivity
import com.class100.poseidon.PsWebActivity
import kotlinx.android.synthetic.main.activity_poseidon_devops.*

class DevOpsActivity : OcActivity() {
    companion object {
        private val TAG = DevOpsActivity::javaClass.name
        private const val DEV_OPS_URL_HISTORY = "dev_ops_url_history";

        fun launch(context: Context) {
            context.startActivity(Intent(context, DevOpsActivity::class.java))
        }
    }

    override fun getContentLayout(): Int {
        return R.layout.activity_poseidon_devops
    }

    override fun init() {
        val data = AtPrefs.get(DEV_OPS_URL_HISTORY, "");
        et_url.setText(data)
        btn_go.setOnClickListener {
            PsWebActivity.launch(it.context, et_url.text.toString())
            AtPrefs.put(DEV_OPS_URL_HISTORY, et_url.text.toString())
        }
    }
}