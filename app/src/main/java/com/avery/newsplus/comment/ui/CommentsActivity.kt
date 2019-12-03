package com.avery.newsplus.comment.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.avery.newsplus.R
import com.avery.newsplus.base.BaseActivity

/**
 * 新闻评论Activity
 *
 * @author Avery
 */

class CommentsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actvity_commnets)
        if (savedInstanceState == null) {
            attachFragment()
        }
    }

    private fun attachFragment() {
        val fragment = CommentsFragment()
        fragment.arguments = bundleOf("newsId" to intent.getStringExtra("newsId"))
        supportFragmentManager
            .beginTransaction()
            .add(R.id.comments_fragment_holder, fragment)
            .commitAllowingStateLoss()
    }
}