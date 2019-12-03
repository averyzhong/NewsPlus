package com.avery.newsplus.details.ui


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.avery.newsplus.R
import com.avery.newsplus.api.NewsService
import com.avery.newsplus.api.model.NewsDetail
import com.avery.newsplus.api.model.NewsMetaItem
import com.avery.newsplus.api.model.Resource
import com.avery.newsplus.base.BaseFragment
import com.avery.newsplus.comment.ui.CommentsActivity
import com.avery.newsplus.details.repository.NewsDetailRepository
import com.avery.newsplus.details.viewmodel.NewsDetailViewModel
import com.avery.newsplus.extentions.database
import com.avery.newsplus.extentions.getApiService
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_details.*
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter

/**
 * 新闻详情Fragment
 *
 * @author Avery
 */

class DetailsFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_details
    private var newsMetaItem: NewsMetaItem? = null
    private val viewModel by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val api = getApiService(NewsService::class.java)
                val dao = database().favoriteDao()
                val repository = NewsDetailRepository(api, dao)
                @Suppress("UNCHECKED_CAST")
                return NewsDetailViewModel(repository) as T
            }

        })[NewsDetailViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        extractArgs()
        loadData()
    }

    private fun extractArgs() {
        arguments?.let {
            newsMetaItem = it.getParcelable("newsMetaItem") as? NewsMetaItem
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUIElement()
        setupEventListeners()
        subscribe()
    }

    private fun setupUIElement() {
        arguments?.let { it ->
            newsMetaItem?.let {
                tvTitle.text = it.title
                activity?.let { activity ->
                    Glide.with(activity).load(it.headPic).into(ivPicture)
                }
            }
        }
    }

    private fun setupEventListeners() {
        btnShowComments.setOnClickListener {
            val intent = Intent(context, CommentsActivity::class.java)
            intent.putExtra("newsId", viewModel.getNewsId())
            context?.startActivity(intent)
        }
        floatBtn.setOnClickListener {
            Snackbar.make(view!!, "TODO", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun loadData() {
        newsMetaItem?.let {
            viewModel.loadDetail(it.aid ?: "")
        }

    }

    private fun subscribe() {
        viewModel.newDetail.observe(viewLifecycleOwner, Observer {
            showDetail(it)
        })

    }

    private fun showDetail(resource: Resource<NewsDetail>) {
        when (resource.status) {
            Resource.Status.LOADING -> tvContent.text = "Loading......"
            Resource.Status.ERROR -> tvContent.text = "Error"
            Resource.Status.SUCCESS -> {
                with(resource.data!!) {
                    tvContent.setHtml(content!!, HtmlHttpImageGetter(tvContent))
                    btnShowComments.text = getString(R.string.show_comments, replyCount)
                }
            }
        }
    }
}
