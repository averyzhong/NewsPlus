package com.avery.newsplus.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.avery.newsplus.api.model.Resource
import com.avery.newsplus.api.model.NewsDetail
import com.avery.newsplus.details.repository.NewsDetailRepository
import com.fonsview.mangotv.extentions.launch

class NewsDetailViewModel(
    private val repository: NewsDetailRepository
) : ViewModel() {

    private val newsIdLiveData = MutableLiveData<String>()

    val newDetail: LiveData<Resource<NewsDetail>> = Transformations.switchMap(newsIdLiveData) { newsId ->
        val newsDetailLiveData = MutableLiveData<Resource<NewsDetail>>()
        newsDetailLiveData.value = Resource.loading("loading")
        launch {
            val newsDetailsResp = repository.getNewsDetails(newsId)
            if (newsDetailsResp.isSuccessful) {
                newsDetailLiveData.value = Resource.success(newsDetailsResp.body()?.data)
            } else {
                newsDetailLiveData.value = Resource.error("error")
            }
        }
        return@switchMap newsDetailLiveData
    }

    fun loadDetail(newId: String) {
        newsIdLiveData.value = newId
    }

    fun getNewsId() = newsIdLiveData.value
}