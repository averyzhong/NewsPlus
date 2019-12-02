package com.avery.newsplus.api.model

/**
 * 通用数据资源对象，包含数据的状态和描述
 *
 * @author Avery
 */

class Resource<T> private constructor(
    val data: T?,
    val message: String,
    val status: Status
) {

    enum class Status {
        LOADING, SUCCESS, ERROR
    }

    companion object {
        fun <T> success(data: T?) =
            Resource(data, "SUCCESS", Status.SUCCESS)
        fun <T> error(message: String) =
            Resource<T>(null, message, Status.ERROR)
        fun <T> loading(message: String) = Resource<T>(
            null,
            message,
            Status.LOADING
        )
    }
}