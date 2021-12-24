package com.example.jetpackcomposeinfo.data.model

/**
 * @Author: Fernando Serra
 */

data class Meta (
    val totalPages: Long,
    val currentPage: Long,
    val nextPage: Any? = null,
    val perPage: Long,
    val totalCount: Long
)
