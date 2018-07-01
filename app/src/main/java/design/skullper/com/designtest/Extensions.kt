package design.skullper.com.designtest

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.support.annotation.*
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.text.Editable
import android.text.Html
import android.text.Spanned
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import java.util.*

/**
 * Created by pugman on 23.08.17.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

//GLOBAL
val appContext: Context = App.context

val appRes: Resources = App.context.resources

//VIEWS
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false)
        : View = LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

fun Context.inflate(@LayoutRes layoutRes: Int,
                    root: ViewGroup? = null,
                    attachToRoot: Boolean = false)
        : View = LayoutInflater.from(this)
        .inflate(layoutRes, root, attachToRoot)

fun EditText.onTextChange(block: (String?, Int, Int, Int) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) = Unit

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            block(s?.toString(), start, before, count)
        }
    })
}

//SPANNABLE, TEXT
fun String.withHtml(): Spanned {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        return Html.fromHtml(this)
    }
}

val EditText.string: String
    get() = this.text.toString()

//OTHER
inline fun consume(f: () -> Unit): Boolean {
    f()
    return true
}

//RESOURCES
fun getString(@StringRes source: Int, vararg params: Any?)
        : String = String.format(Locale.getDefault(), appRes.getString(source), *params)

fun getString(@StringRes source: Int): String = appRes.getString(source)

fun getColor(@ColorRes source: Int): Int = ContextCompat.getColor(appContext, source)

fun getDimens(@DimenRes source: Int) = appRes.getDimensionPixelOffset(source)

fun getFont(@FontRes source: Int) = ResourcesCompat.getFont(appContext, source)