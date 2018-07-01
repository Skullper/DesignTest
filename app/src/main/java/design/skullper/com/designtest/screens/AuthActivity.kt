package design.skullper.com.designtest.screens

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import design.skullper.com.designtest.R
import design.skullper.com.designtest.withHtml
import kotlinx.android.synthetic.main.activity_auth.*

/**
 * Created by skullper on 01.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
        tv_auth_label_sign_in.text = getString(R.string.auth_sign_in_label).withHtml()
    }
}