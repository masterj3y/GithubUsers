package github.masterj3y.designsystem.theme

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DesignSystemViewModel(application: Application) : AndroidViewModel(application) {

    private val preferences = Preferences.instance(application.baseContext)

    private val _darkModeEnabled = MutableStateFlow(preferences.isDarkModeEnabled)
    val darkModeEnabled = _darkModeEnabled.asStateFlow()

    fun enableDarkMode(enable: Boolean) {
        preferences.isDarkModeEnabled = enable
        _darkModeEnabled.value = enable
    }
}