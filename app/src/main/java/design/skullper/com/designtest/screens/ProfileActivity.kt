package design.skullper.com.designtest.screens

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import design.skullper.com.designtest.R
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.widget_toolbar.*


/**
 * Created by skullper on 01.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

class ProfileActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.title = ""
        tv_toolbar_title.text = "Profile"
        val semiTransparentGrey = Color.argb(155, 155, 155, 235)
        iv_profile_cover.setColorFilter(semiTransparentGrey, PorterDuff.Mode.SRC_ATOP)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_profile, menu)
        return super.onCreateOptionsMenu(menu)
    }
}