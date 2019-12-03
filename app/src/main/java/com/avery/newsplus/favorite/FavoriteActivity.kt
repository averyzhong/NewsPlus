package com.avery.newsplus.favorite

import android.os.Bundle
import com.avery.newsplus.R
import com.avery.newsplus.base.BaseActivity

/**
 * 新闻收藏Activity
 *
 * @author Avery
 */

class FavoriteActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        if (savedInstanceState == null) {
            attachFragment()
        }
    }

    private fun attachFragment() {

    }
}