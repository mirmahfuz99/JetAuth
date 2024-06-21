package com.jetauth.core

abstract class UseCase<Type, Params> {
    abstract suspend fun call(params: Params): Type
}
