package br.cericatto.mobile_ui.injection.module

import br.cericatto.domain.executor.PostExecutionThread
import br.cericatto.mobile_ui.UiThread
//import br.cericatto.mobile_ui.bookmarked.BookmarkedActivity
import br.cericatto.mobile_ui.browse.BrowseActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {
    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesBrowseActivity(): BrowseActivity

//    @ContributesAndroidInjector
//    abstract fun contributesBookmarkedActivity(): BookmarkedActivity
}