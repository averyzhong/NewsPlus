package com.avery.newsplus.home.adapter

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.avery.newsplus.api.model.Category
import com.avery.newsplus.list.ui.NewsListFragment

/**
 * 新闻首页数据适配器
 *
 * @author Avery
 */

class ListFragmentAdapter(
    fragmentManager: FragmentManager,
    private val categories: List<Category>
) : FragmentPagerAdapter(fragmentManager) {

    private val listFragments: MutableList<NewsListFragment> = mutableListOf()

    init {
        categories.forEach {
            createListFragment(it)
        }
    }

    override fun getItem(position: Int) = listFragments[position]

    override fun getCount() = listFragments.size

    override fun getPageTitle(position: Int) = categories[position].name

    private fun createListFragment(category: Category) {
        val listFragment = NewsListFragment()
        val args = Bundle()
        args.putInt("categoryId", category.id)
        listFragment.arguments = args
        listFragments.add(listFragment)
    }
}