package github.masterj3y.data.extension

import arrow.core.Either
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.getOrNull
import github.masterj3y.data.exception.FetchDataException

fun <R> ApiResponse<R>.either(): Either<FetchDataException, R> = getOrNull()
    ?.let { Either.Right(it) }
    ?: Either.Left(FetchDataException())