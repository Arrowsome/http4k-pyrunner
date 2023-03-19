package me.arrowsome.pyrunner

sealed interface Result {
    data class Success <out T> (val data: T) : Result
    data class Failure <out E> (val error: E): Result
}