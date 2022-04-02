package com.example.testapplication

import com.example.testapplication.di.AppComponent
import com.example.testapplication.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class ProjectApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        DI.appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        return DI.appComponent
    }
}
object DI {
    lateinit var appComponent: AppComponent
        internal set
}
