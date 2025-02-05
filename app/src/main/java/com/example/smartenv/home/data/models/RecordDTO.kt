package com.example.smartenv.home.data.models

data class RecordDTO(
    val success: Boolean,
    val message: String,
    val error: String?,
    val data: RecordData?
)
