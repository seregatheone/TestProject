package com.example.testapplication.ui

import com.example.testapplication.ui.jokes.JokesFragment
import com.example.testapplication.ui.web.WebFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


//Модуль для контрибьют инжектора. Сделан отдельно, т.к. реализует binds и поэтому должен быть абстрактным
@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun bindListFragment(): JokesFragment

    @ContributesAndroidInjector
    abstract fun bindChangeFieldsFragment(): WebFragment
}