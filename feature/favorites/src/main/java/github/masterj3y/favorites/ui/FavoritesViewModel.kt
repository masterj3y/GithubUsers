package github.masterj3y.favorites.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.masterj3y.coroutines.flow.WhileSubscribedOrRetained
import github.masterj3y.data.repository.UserRepository
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(repository: UserRepository) : ViewModel() {

    val favorites = repository.favoriteUsers.stateIn(
        scope = viewModelScope,
        started = WhileSubscribedOrRetained,
        initialValue = emptyList()
    )
}