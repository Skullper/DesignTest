package design.skullper.com.designtest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_main_first.setOnClickListener(this)
        btn_main_sec.setOnClickListener(this)
        btn_main_third.setOnClickListener(this)
        btn_main_fourth.setOnClickListener(this)
    }

    override fun onClick(v: View) = when (v.id) {
        R.id.btn_main_first -> TODO()
        R.id.btn_main_sec -> TODO()
        R.id.btn_main_third -> TODO()
        R.id.btn_main_fourth -> TODO()
        else -> throw IllegalArgumentException("Wrong button id")
    }
}
