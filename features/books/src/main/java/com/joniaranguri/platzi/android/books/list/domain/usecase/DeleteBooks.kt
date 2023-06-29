package com.joniaranguri.platzi.android.books.list.domain.usecase

import androidx.annotation.VisibleForTesting
import com.joniaranguri.platzi.android.books.list.domain.repository.BooksRepository
import com.joniaranguri.platzi.android.core.usecase.LocalUseCase
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class DeleteBooks @Inject constructor(
    @get:VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
    internal val booksRepository: BooksRepository
) : LocalUseCase<Unit, Unit>() {

    override suspend fun FlowCollector<Unit>.execute(params: Unit) {
        booksRepository.deleteBooks()
        emit(Unit)
    }
}