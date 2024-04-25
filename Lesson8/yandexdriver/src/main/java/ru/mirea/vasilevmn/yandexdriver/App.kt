package ru.mirea.vasilevmn.yandexdriver

import android.app.Application
import com.yandex.mapkit.MapKitFactory

class App : Application() {

    companion object {
        private const val MAPKIT_API_KEY = "fa1ff15b-62d1-4721-9fb1-98ba37ffa7a5"
    }

    override fun onCreate() {
        super.onCreate()

        MapKitFactory.setApiKey(MAPKIT_API_KEY)
    }
}