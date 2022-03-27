package com.retrofit2.converter

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class JsonConverterFactory private constructor(private val json: Json) : Converter.Factory() {
    @OptIn(ExperimentalSerializationApi::class)
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ) = Converter<ResponseBody, Any> {
        json.decodeFromString(serializer(type), it.string())
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<out Annotation>,
        methodAnnotations: Array<out Annotation>,
        retrofit: Retrofit
    ) = Converter<Any, RequestBody> {
        RequestBody.create(
            MediaType.get("application/json; charset=UTF-8"),
            json.encodeToString(serializer(type), it)
        )
    }

    companion object {
        fun create(
            json: Json? = Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
        ): JsonConverterFactory {
            requireNotNull(json)
            return JsonConverterFactory(json)
        }
    }
}