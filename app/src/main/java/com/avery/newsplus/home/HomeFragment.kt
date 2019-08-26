package com.avery.newsplus.home


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
import com.avery.newsplus.home.repository.CategoryRepository
import com.avery.newsplus.home.viewmodel.CategoryViewModel
import com.avery.newsplus.home.adapter.ListFragmentAdapter
import com.avery.newsplus.api.model.Category
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return CategoryViewModel(CategoryRepository()) as T
            }
        })[CategoryViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe()
        load()
    }

    private fun subscribe() {
        viewModel.categoryList.observe(this, Observer {
            setupViewPager(it)
        })
    }

    private fun setupViewPager(categories: List<Category>) {
        tabLayout.setupWithViewPager(viewPager, true)
        viewPager.adapter = ListFragmentAdapter(childFragmentManager, categories)
    }

    private fun load() {
        viewModel.loadCategories()
    }

}
