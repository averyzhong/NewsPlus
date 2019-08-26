package com.avery.newsplus.home.adapter

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.avery.newsplus.api.model.Category
import com.avery.newsplus.list.NewsListFragment

class ListFragmentAdapter(
    fragmentManager: FragmentManager,
    private val categories: List<Category>
) : FragmentStatePagerAdapter(fragmentManager) {

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