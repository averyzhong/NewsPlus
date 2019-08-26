package com.avery.newsplus.details


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.avery.newsplus.R
import com.avery.newsplus.api.model.Resource
import com.avery.newsplus.api.NewsService
import com.avery.newsplus.api.ServiceFactory
import com.avery.newsplus.api.model.NewsDetail
import com.avery.newsplus.api.model.NewsMetaItem
import com.avery.newsplus.comment.CommentsActivity
import com.avery.newsplus.details.repository.NewsDetailRepository
import com.avery.newsplus.details.viewmodel.NewsDetailViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_details.*
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter


@Suppress("UNCHECKED_CAST")
class DetailsFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val api = ServiceFactory.getService(NewsService::class.java)
                val repository = NewsDetailRepository(api)
                return NewsDetailViewModel(repository) as T
            }

        })[NewsDetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUIElement()
        setupEventListeners()
        subscribe()
        loadData()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupUIElement() {
        arguments?.let { it ->
            val newsMetaItem = it.getParcelable("newsMetaItem") as? NewsMetaItem
            newsMetaItem?.let {
                tvTitle.text = it.title
                activity?.let { activity ->
                    Glide.with(activity)
                        .load(it.headPic)
                        .into(ivPicture)
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
    }

    private fun loadData() {
        val newsMetaItem = arguments?.getParcelable("newsMetaItem") as? NewsMetaItem
        viewModel.loadDetail(newsMetaItem?.aid ?: "")
    }

    private fun subscribe() {
        viewModel.newDetail.observe(this, Observer {
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
