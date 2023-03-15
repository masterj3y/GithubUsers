package github.masterj3y.profile.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import github.masterj3y.coroutines.flow.WhileSubscribedOrRetained
import github.masterj3y.data.model.UserProfile
import github.masterj3y.data.repository.UserRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: UserRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val userName = savedStateHandle.getStateFlow<String?>(USER_NAME_KEY, null)

    val userProfile =
        userName.filterNotNull().flatMapLatest { userName -> repository.getUserProfile(userName) }
            .stateIn(
                scope = viewModelScope,
                started = WhileSubscribedOrRetained,
                initialValue = null
            )

    val followers =
        userName.filterNotNull().flatMapLatest { userName -> repository.getUserFollowers(userName) }
            .stateIn(
                scope = viewModelScope,
                started = WhileSubscribedOrRetained,
                initialValue = emptyList()
            )

    val following =
        userName.filterNotNull().flatMapLatest { userName -> repository.getUserFollowing(userName) }
            .stateIn(
                scope = viewModelScope,
                started = WhileSubscribedOrRetained,
                initialValue = emptyList()
            )

    val isUserProfileSaved =
        userName.filterNotNull()
            .flatMapLatest { userName -> repository.isUserProfileSaved(userName) }
            .stateIn(
                scope = viewModelScope,
                started = WhileSubscribedOrRetained,
                initialValue = null
            )

    fun loadUserProfile(userName: String) = savedStateHandle.set(USER_NAME_KEY, userName)

    fun saveUserProfile(userProfile: UserProfile) {
        viewModelScope.launch { repository.saveUserProfile(userProfile) }
    }

    fun deleteUserProfile(userProfile: UserProfile) {
        viewModelScope.launch { repository.deleteUserProfile(userProfile) }
    }

    companion object {
        private const val USER_NAME_KEY = "user-name"
    }
}