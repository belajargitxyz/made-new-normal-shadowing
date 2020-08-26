package alfianyabdullah.submission.made

import alfianyabdullah.submission.core.di.databaseModule
import alfianyabdullah.submission.core.di.networkModule
import alfianyabdullah.submission.core.di.repositoryModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule
                )
            )
        }
    }
}