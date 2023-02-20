package com.retrofit2.converter

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder
import kotlinx.serialization.serializer
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class JsonConverterFactory private constructor(
    private val json: Json
) : Converter.Factory() {
    companion object {
        private val jsonDefault: Json by lazy {
            Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
        }

        @JvmStatic
        @JvmOverloads
        fun create(
            json: Json = jsonDefault
        ): JsonConverterFactory = JsonConverterFactory(json)

        fun create(
            configuration: JsonBuilder.() -> Unit
        ): JsonConverterFactory = create(Json(builderAction = configuration))
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> = Converter<ResponseBody, Any> {
        json.decodeFromString(serializer(type), it.string())
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<out Annotation>,
        methodAnnotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody> = Converter<Any, RequestBody> {
        json.encodeToString(serializer(type), it).toRequestBody()
    }
}