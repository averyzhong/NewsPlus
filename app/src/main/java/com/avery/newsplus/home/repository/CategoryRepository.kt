package com.avery.newsplus.home.repository

import com.avery.newsplus.api.model.Category

class CategoryRepository {

    fun getCategories() = mutableListOf<Category>().also {
        with(it) {
            add(Category(9, "头条"))
            add(Category(1, "娱乐"))
            add(Category(6, "体育"))
            add(Category(7, "科技"))
            add(Category(4, "财经"))
            add(Category(2, "军事"))
            add(Category(3, "汽车"))

        }
    }

}