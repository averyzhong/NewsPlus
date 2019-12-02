package com.avery.newsplus.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avery.newsplus.home.repository.CategoryRepository
import com.avery.newsplus.api.model.Category
import com.avery.newsplus.extentions.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 新闻分类ViewModel
 *
 * @author Avery
 */

class CategoryViewModel(
    private val repository: CategoryRepository
) : ViewModel() {

    private val mutableCategoriesLiveData by lazy {
        MutableLiveData<List<Category>>()
    }

    val categoryList: LiveData<List<Category>> = mutableCategoriesLiveData


    fun loadCategories() = launch {
        val categories = loadInBackground()
        mutableCategoriesLiveData.value = categories
    }

    private suspend fun loadInBackground() = withContext(Dispatchers.Default) {
        repository.getCategories()
    }

}