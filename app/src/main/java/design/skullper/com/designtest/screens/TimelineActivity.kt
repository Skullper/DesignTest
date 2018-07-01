package design.skullper.com.designtest.screens

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.text.Spannable
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import design.skullper.com.designtest.R
import design.skullper.com.designtest.getDimens
import design.skullper.com.designtest.inflate
import design.skullper.com.designtest.models.CalendarItem
import design.skullper.com.designtest.models.TimelineItem
import kotlinx.android.synthetic.main.activity_timeline.*
import kotlinx.android.synthetic.main.item_calendar.view.*
import kotlinx.android.synthetic.main.item_timeline.view.*
import kotlinx.android.synthetic.main.widget_toolbar.*
import travel.ithaka.android.horizontalpickerlib.PickerLayoutManager
import java.util.*


/**
 * Created by skullper on 01.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

class TimelineActivity : AppCompatActivity() {

    private val guys = intArrayOf(R.drawable.person1, R.drawable.person2, R.drawable.person3,
            R.drawable.person4, R.drawable.person5)
    private val days = listOf("January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline)
        //toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.title = ""
        tv_toolbar_title.text = "Timeline"
        //content
        val semiTransparentBlue = Color.argb(155, 155, 155, 235)
        iv_timeline_cover.setColorFilter(semiTransparentBlue, PorterDuff.Mode.SRC_ATOP)
        initCalendar()
        initTimeline()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_feed, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun initTimeline() {
        rv_timeline.layoutManager = LinearLayoutManager(this)
        rv_timeline.itemAnimator = DefaultItemAnimator()
        rv_timeline.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rv_timeline.setHasFixedSize(true)
        val items = generateTimeline()
        rv_timeline.adapter = object : RecyclerView.Adapter<TimelineViewHolder>() {

            override fun getItemId(position: Int): Long = position.toLong()

            override fun getItemViewType(position: Int): Int = position

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                    : TimelineViewHolder = TimelineViewHolder(parent.inflate(R.layout.item_timeline))

            override fun getItemCount(): Int = items.size

            override fun onBindViewHolder(holder: TimelineViewHolder, position: Int) = holder.bind(items[position])
        }
    }

    inner class TimelineViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val indicator: View = view.indicator_item_timeline
        private val tvTime: TextView = view.tv_item_timeline_time
        private val tvTitle: TextView = view.tv_item_timeline_title
        private val tvName: TextView = view.tv_item_timeline_name
        private val container: LinearLayout = view.container_timeline

        fun bind(item: TimelineItem) = with(item) {
            indicator.backgroundTintList = ContextCompat.getColorStateList(this@TimelineActivity,
                    if (isSet) R.color.colorGreen else R.color.colorRed)
            val timeSpan = TextAppearanceSpan(this@TimelineActivity, R.style.TextStyleTime)
            val timeResult = time.plus(if (isDay) " PM" else " AM")
            val spannableString = SpannableString(timeResult)
            spannableString.setSpan(timeSpan, time.length, timeResult.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            tvTime.text = spannableString
            tvTitle.text = title
            tvName.text = name
            if (container.childCount > 0) container.removeAllViews()
            people.take(Random().nextInt(2)).map {
                val image = ImageView(container.context)
                val imageSize = getDimens(R.dimen.timeline_photo_size)
                val imagePadding = getDimens(R.dimen.timeline_photo_padding)
                image.layoutParams = LinearLayout.LayoutParams(imageSize, imageSize)
                image.setPadding(imagePadding, imagePadding, imagePadding, imagePadding)
                image.setImageResource(it)
                container.addView(image)
            }
            return@with
        }
    }

    private fun generateTimeline(): List<TimelineItem> {
        val tempList = arrayListOf<TimelineItem>()
        for (i in 1..10) {
            tempList.add(TimelineItem(i % 2 == 0, "$i:".plus(String.format("%02d", (54 / i))),
                    i % 3 == 0, guys))
        }
        return tempList
    }

    private fun initCalendar() {
        val pickerLayoutManager = PickerLayoutManager(this, PickerLayoutManager.HORIZONTAL, false)
        pickerLayoutManager.isChangeAlpha = true
        pickerLayoutManager.scaleDownBy = 0.99f
        pickerLayoutManager.scaleDownDistance = 0.9f
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rv_calendar)
        rv_calendar.layoutManager = pickerLayoutManager
        rv_calendar.itemAnimator = DefaultItemAnimator()
        rv_calendar.setHasFixedSize(true)
        val items = generateCalendar()
        rv_calendar.adapter = object : RecyclerView.Adapter<CalendarViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                    : CalendarViewHolder = CalendarViewHolder(parent.inflate(R.layout.item_calendar))

            override fun getItemCount(): Int = items.size

            override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) = holder.bind(items[position])
        }
        rv_calendar.smoothScrollToPosition(1)
    }

    inner class CalendarViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvDate: TextView = view.tv_item_calendar_date
        private val tvMonth: TextView = view.tv_item_calendar_month
        private val tvEvents: TextView = view.tv_item_calendar_events

        @SuppressLint("SetTextI18n")
        fun bind(item: CalendarItem) = with(item) {
            tvDate.text = date.toString()
            tvMonth.text = month
            tvEvents.text = "$eventsCount events"
        }
    }

    private fun generateCalendar(): List<CalendarItem> {
        val tempList = arrayListOf<CalendarItem>()
        for (i in 12..23) {
            tempList.add(CalendarItem(i, days[Random().nextInt(11)], 64 / i))
        }
        return tempList
    }

}