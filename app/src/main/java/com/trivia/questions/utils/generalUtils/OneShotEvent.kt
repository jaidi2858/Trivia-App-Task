package com.trivia.questions.utils.generalUtils

open class OneShotEvent<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}