package design.skullper.com.designtest.models

import design.skullper.com.designtest.R

/**
 * Created by skullper on 01.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

data class FeedItem(val likes: Int = 0,
                    val comments: Int = 0,
                    val isLiked: Boolean = false,
                    val image: Int = R.drawable.ic_pug,
                    val title: String = "Past Perfect Continuous, як і Past Perfect (https://t.me/uaenglish/244), теж досить рідко використовується і теж вживається у єдиному випадку — наголосити, що дія відбулася до певної дії або моменту у минулому.")