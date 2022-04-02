package com.example.testapplication.di

import android.app.Application
import com.example.testapplication.ProjectApplication
import com.example.testapplication.data.DataModule
import com.example.testapplication.ui.UIModule
import com.example.testapplication.ui.jokes.JokesViewModelFactory
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Scope

@[AppScope Component(modules = [AppModule::class, AndroidInjectionModule::class])]
interface AppComponent: AndroidInjector<ProjectApplication> {

    fun provideJokesViewModelFactory(): JokesViewModelFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}
@Module(includes = [DataModule::class, UIModule::class])
class AppModule

@Scope
annotation class AppScope