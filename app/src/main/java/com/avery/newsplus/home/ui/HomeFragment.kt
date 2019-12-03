package com.avery.newsplus.home.ui


import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.avery.newsplus.R
import com.avery.newsplus.api.model.Category
import com.avery.newsplus.base.BaseFragment
import com.avery.newsplus.extentions.startActivity
import com.avery.newsplus.favorite.FavoriteActivity
import com.avery.newsplus.home.adapter.ListFragmentAdapter
import com.avery.newsplus.home.repository.CategoryRepository
import com.avery.newsplus.home.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * 新闻首页Fragment
 *
 * @author Avery
 */

class HomeFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_home
    private val viewModel by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return CategoryViewModel(CategoryRepository()) as T
            }
        })[CategoryViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        load()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEventListeners()
        subscribe()
    }

    private fun setupEventListeners() {
        bntMenu.setOnClickListener {
            if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navItemFavorite -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    startActivity(FavoriteActivity::class.java)
                    true
                }
                R.id.navItemAbout -> {
                    drawerLayout.closeDrawer(GravityCompat.START)
                    startActivity(FavoriteActivity::class.java)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun subscribe() {
        viewModel.categoryList.observe(viewLifecycleOwner, Observer {
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
