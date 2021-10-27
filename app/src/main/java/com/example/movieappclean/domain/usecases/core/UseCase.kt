package com.example.movieappclean.domain.usecases.core

import androidx.annotation.VisibleForTesting
import com.example.movieappclean.data.dataSource.remote.models.core.IRequest
import com.example.movieappclean.domain.entities.common.Status
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

abstract class UseCase<in P : IRequest, R> {

    private val dataMutable = Channel<Status<R>>(Channel.RENDEZVOUS, BufferOverflow.DROP_OLDEST)
    val data: Flow<Status<R>> get() = dataMutable.receiveAsFlow().flowOn(IO)


    @VisibleForTesting
    protected abstract suspend fun run(param: P): Flow<Status<R>>

    suspend operator fun invoke(
        param: P
    ) {
        run(param = param)
            .collect {
                dataMutable.send(it)
            }

    }
}