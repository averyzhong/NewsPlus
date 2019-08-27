package com.avery.newsplus.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class LazyLoadFragment : Fragment() {
    private var isDataLoaded = false
    private var isViewCreated = false
    private var isVisibleToUser = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewCreated = true
        startLoadData()
    }

    override fun setUserVisibleHint(yes: Boolean) {
        super.setUserVisibleHint(yes)
        isVisibleToUser = yes
        startLoadData()
    }

    private fun startLoadData() {
        if (isViewCreated && isVisibleToUser && !isDataLoaded) {
            isDataLoaded = true
            loadData()
        }

    }

    protected abstract fun loadData()
}