package com.avery.newsplus.list.ui


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.avery.newsplus.R
import com.avery.newsplus.api.NewsService
import com.avery.newsplus.api.ServiceFactory
import com.avery.newsplus.base.LazyLoadFragment
import com.avery.newsplus.details.ui.DetailsActivity
import com.avery.newsplus.list.adapter.NewsMetaAdapter
import com.avery.newsplus.list.repository.NewsListRepository
import com.avery.newsplus.list.viewmodel.NewsListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * 新闻列表Fragment
 *
 * @author Avery
 */

class NewsListFragment : LazyLoadFragment() {
    override val layoutRes = R.layout.fragment_list
    private lateinit var adapter: NewsMetaAdapter

    private val viewModel by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val api = ServiceFactory.getService(NewsService::class.java)
                val repository = NewsListRepository(api)
                @Suppress("UNCHECKED_CAST")
                return NewsListViewModel(repository) as T
            }

        })[NewsListViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUIElements()
        subscribe()
    }

    private fun setupUIElements() {
        adapter = NewsMetaAdapter {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("newsMetaItem", it)
            context?.startActivity(intent)

        }
        adapter.registerAdapterDataObserver(observer)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
    }

    private fun subscribe() {
        viewModel.newsMetaItems.observe(viewLifecycleOwner, Observer {
            hideLoadViewIfNeeds()
            adapter.submitList(it)

        })
    }

    override fun loadData() {
        viewModel.loadNewsList(arguments?.getInt("categoryId") ?: 1)
    }

    private val observer =  object: RecyclerView.AdapterDataObserver() {
        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            hideLoadViewIfNeeds()
        }
    }

    private fun hideLoadViewIfNeeds() {
        loadingView?.let {
            if (it.isVisible && adapter.itemCount > 0) {
                it.isVisible = false
                adapter.unregisterAdapterDataObserver(observer)
            }
        }
    }


}
