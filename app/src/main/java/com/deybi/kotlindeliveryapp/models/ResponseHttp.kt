package com.deybi.kotlindeliveryapp.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class ResponseHttp(
    @SerializedName("message") val message: String,
    @SerializedName("success") val isSuccess: Boolean,
    @SerializedName("data") val data: JsonObject,
    @SerializedName("error") val error: JsonObject,
) {
    override fun toString(): String {
        return "ResponseHttp(message='$message', success=$isSuccess, data=$data, error='$error')"
    }
}