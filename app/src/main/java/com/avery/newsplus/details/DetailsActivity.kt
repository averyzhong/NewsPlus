package com.avery.newsplus.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.avery.newsplus.R

/**
 * 新闻详情Activity
 *
 * @author Avery
 */

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        if (savedInstanceState == null) {
            attachFragment()
        }
    }

    private fun attachFragment() {
        val detailsFragment = DetailsFragment()
        detailsFragment.arguments = bundleOf("newsMetaItem" to intent.getParcelableExtra("newsMetaItem"))
        supportFragmentManager.beginTransaction()
            .add(R.id.detailsFragmentHolder, detailsFragment)
            .commitAllowingStateLoss()
    }
}