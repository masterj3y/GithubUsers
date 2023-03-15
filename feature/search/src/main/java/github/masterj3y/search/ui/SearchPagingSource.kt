package github.masterj3y.search.ui

import github.masterj3y.data.model.BasicUser
import github.masterj3y.data.paging.BasicPagingSource
import github.masterj3y.data.repository.UserRepository

class SearchPagingSource(repository: UserRepository, userName: String) : BasicPagingSource<BasicUser>(
    request = { page ->
        repository.searchUsers(userName, page)
    }
)