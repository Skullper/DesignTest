package design.skullper.com.designtest.screens

import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import design.skullper.com.designtest.R
import design.skullper.com.designtest.inflate
import design.skullper.com.designtest.models.FeedItem
import kotlinx.android.synthetic.main.activity_feed.*
import kotlinx.android.synthetic.main.item_feed.view.*

/**
 * Created by skullper on 01.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

class FeedActivity : AppCompatActivity(), ViewPager.OnPageChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        //toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.title = ""
        toolbar.findViewById<TextView>(R.id.tv_toolbar_title).text = "NewsFeed"
        //Rest of the screen content
        tab_layout.getTabAt(1)?.select()
        initPager()
        view_pager_indicator.setupWithViewPager(view_pager)
        view_pager_indicator.addOnPageChangeListener(this)
        initFeed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_feed, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPageScrollStateChanged(state: Int) = Unit

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = Unit

    override fun onPageSelected(position: Int) = Unit

    private fun initPager() {
        view_pager.adapter = object : PagerAdapter() {
            override fun isViewFromObject(view: View, item: Any): Boolean = item == view

            override fun getCount(): Int = 5

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                val image = ImageView(container.context)
                image.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT)
                image.scaleType = ImageView.ScaleType.CENTER_CROP
                image.setImageResource(R.drawable.ronaldo)
                container.addView(image)
                return image
            }

            override fun destroyItem(container: ViewGroup, position: Int, item: Any) = container.removeView(item as View)

        }
    }

    private fun initFeed() {
        rv_feed.layoutManager = LinearLayoutManager(this)
        rv_feed.itemAnimator = DefaultItemAnimator()
        rv_feed.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        rv_feed.setHasFixedSize(true)
        val items = generateFeed()
        rv_feed.adapter = object : RecyclerView.Adapter<FeedViewHolder>() {

            override fun getItemId(position: Int): Long = position.toLong()

            override fun getItemViewType(position: Int): Int = position

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                    : FeedViewHolder = FeedViewHolder(parent.inflate(R.layout.item_feed))

            override fun getItemCount(): Int = items.size

            override fun onBindViewHolder(holder: FeedViewHolder, position: Int) = holder.bind(items[position])
        }
    }

    inner class FeedViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val ivImage: ImageView = view.iv_item_feed
        private val tvTitle: TextView = view.tv_item_feed_title
        private val tvLikesCount: TextView = view.tv_item_feed_like
        private val tvCommentCount: TextView = view.tv_item_feed_comments

        fun bind(item: FeedItem) = with(item) {
            ivImage.setImageResource(image)
            tvTitle.text = title
            tvLikesCount.text = " ".plus(getString(R.string.feed_likes, likes))
            if (isLiked) tvLikesCount.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_liked, 0, 0, 0)
            tvCommentCount.text = " ".plus(getString(R.string.feed_comments, comments))
        }
    }

    private fun generateFeed(): List<FeedItem> {
        val tempList = arrayListOf<FeedItem>()
        for (i in 1..15) {
            tempList.add(FeedItem(i * 3, i * 2, i % 2 == 0))
        }
        return tempList
    }
}