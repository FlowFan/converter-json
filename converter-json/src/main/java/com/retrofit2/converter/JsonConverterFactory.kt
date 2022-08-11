package com.retrofit2.converter

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder
import kotlinx.serialization.serializer
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

@OptIn(ExperimentalSerializationApi::class)
class JsonConverterFactory private constructor(private val json: Json) : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ) = Converter<ResponseBody, Any> {
        json.decodeFromString(serializer(type), it.string())
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<out Annotation>,
        methodAnnotations: Array<out Annotation>,
        retrofit: Retrofit
    ) = Converter<Any, RequestBody> {
        json.encodeToString(serializer(type), it).toRequestBody()
    }

    companion object {
        private val jsonDefault by lazy {
            Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
        }

        @JvmStatic
        @JvmOverloads
        fun create(
            json: Json = jsonDefault
        ) = JsonConverterFactory(json)

        fun create(configuration: JsonBuilder.() -> Unit) =
            create(Json(builderAction = configuration))
    }
}