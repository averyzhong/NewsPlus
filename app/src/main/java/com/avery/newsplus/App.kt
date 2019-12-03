package com.avery.newsplus

import android.app.Application
import kotlin.properties.Delegates

/**
 * Application
 *
 * @author Avery
 */

class App : Application() {

    companion object {
        private var instance: App by Delegates.notNull()
        fun instance() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}