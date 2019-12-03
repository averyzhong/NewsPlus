package com.avery.newsplus.details.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.avery.newsplus.R
import com.avery.newsplus.base.BaseActivity

/**
 * 新闻详情Activity
 *
 * @author Avery
 */

class DetailsActivity : BaseActivity() {

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