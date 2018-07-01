@file:Suppress("ArrayInDataClass")

package design.skullper.com.designtest.models

/**
 * Created by skullper on 01.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

data class TimelineItem(val isSet: Boolean = false,
                        val time: String = "4:12",
                        val isDay: Boolean = false,
                        val people: IntArray = intArrayOf(),
                        val title: String = "Some title",
                        val name: String = "Some name under it")