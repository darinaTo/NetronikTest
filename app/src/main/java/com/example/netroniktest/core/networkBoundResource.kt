package com.example.netroniktest.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline mapFetched: suspend (RequestType) -> ResultType,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
): Flow<NetworkResult<ResultType>> = flow {
    emit(NetworkResult.Loading)

    val localData = query().firstOrNull()

    if (localData == null || shouldFetch(localData)) {
        try {
            val fetched = withContext(Dispatchers.IO) { fetch() }
            val mapped = mapFetched(fetched)

            emit(NetworkResult.Success(mapped))

            withContext(Dispatchers.IO) {
                saveFetchResult(fetched)
            }
        } catch (e: Exception) {
            emit(NetworkResult.Error(e))
        }
    }
    emitAll(query().map { NetworkResult.Success(it) })

}.distinctUntilChanged()
    .flowOn(Dispatchers.IO)