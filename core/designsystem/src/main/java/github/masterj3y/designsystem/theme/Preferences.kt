package github.masterj3y.designsystem.theme

import android.content.Context
import android.content.SharedPreferences

internal class Preferences private constructor(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("app_theme", Context.MODE_PRIVATE)

    var isDarkModeEnabled: Boolean?
        // using putString and getString because of nullability(that putBoolean and getBoolean are not nullable)
        get() = preferences.getString(DARK_MODE_KEY, null)?.toBoolean()
        set(value) {
            if (value != null)
                preferences.edit().putString(DARK_MODE_KEY, value.toString()).apply()
        }

    companion object {

        private var instance: Preferences? = null

        private const val DARK_MODE_KEY = "dark-mode"

        fun instance(context: Context): Preferences =
            instance ?: Preferences(context).also { instance = it }
    }
}