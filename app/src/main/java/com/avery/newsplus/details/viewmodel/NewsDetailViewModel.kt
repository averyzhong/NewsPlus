package com.avery.newsplus.details.viewmodel

import androidx.lifecycle.*
import com.avery.newsplus.api.model.Resource
import com.avery.newsplus.api.model.NewsDetail
import com.avery.newsplus.api.model.NewsMetaItem
import com.avery.newsplus.db.entity.Favorite
import com.avery.newsplus.details.repository.NewsDetailRepository
import com.avery.newsplus.extentions.launch
import kotlinx.coroutines.CoroutineExceptionHandler

/**
 * 新闻详情ViewModel
 *
 * @author Avery
 */

class NewsDetailViewModel(
    private val repository: NewsDetailRepository
) : ViewModel() {

    private val newsIdLiveData: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    private val addFavoriteResult: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    private val removeFavoriteResult: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    private val checkFavoriteResult: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    val newDetail: LiveData<Resource<NewsDetail>> =
        Transformations.switchMap(newsIdLiveData) { newsId ->
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

    fun addFavoriteResult(): LiveData<Boolean> = addFavoriteResult

    fun removeFavoriteResult(): LiveData<Boolean> = removeFavoriteResult

    fun checkFavoriteResult(): LiveData<Boolean> = checkFavoriteResult

    private fun addFavorite(newsItem: NewsMetaItem?) =
        launch(context = viewModelScope.coroutineContext + CoroutineExceptionHandler { _, _ ->
            addFavoriteResult.value = false
        }) {
            newsItem?.let {
                with(it) {
                    val favorite = Favorite()
                    favorite.newsId = aid
                    favorite.title = title
                    favorite.source = source
                    favorite.publishDate = publishTime
                    favorite.commentCount = clickCount
                    favorite.headerImageUrl = headPic
                    repository.addFavorite(favorite)
                    addFavoriteResult.value = true
                }
            }
        }

    private fun removeFavorite(newsId: String) = launch {
        repository.removeFavorite(newsId)
        removeFavoriteResult.value = true
    }

    fun checkFavorite(newId: String) = launch {
        checkFavoriteResult.value = repository.queryFavorite(newId) != null
    }

    fun handlerFavoriteClick(newsMetaItem: NewsMetaItem?) {
        newsMetaItem?.let {
            launch {
                val favorite = repository.queryFavorite(newsMetaItem.aid!!)
                if (favorite == null) {
                    addFavorite(it)
                } else {
                    removeFavorite(it.aid!!)
                }
                checkFavorite(it.aid!!)
            }
        }
    }
}