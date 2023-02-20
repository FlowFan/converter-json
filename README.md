# JsonConverterFactory

一个用于 Json 序列化的 `Converter.Factory`
，类似于 [GsonConverterFactory](https://github.com/square/retrofit/tree/master/retrofit-converters/gson)，因 Retrofit 2
并没有提供 [Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization/) 的 Converter
库，所以封装了此 [JsonConverterFactory](https://github.com/fan1138612367/converter-json)
，与 [Kotlin Serialization Converter](https://github.com/JakeWharton/retrofit2-kotlinx-serialization-converter)功能相同。

# 使用

### 添加依赖

```groovy
implementation 'io.github.fantyflow:converter-json:1.5.0'
```

### 1.直接使用

> 默认配置：
>
> - coerceInputValues = true：如果Json字段是Null则使用数据类中定义的默认值
>
> - ignoreUnknownKeys = true：允许数据类中的定义的字段比Json字段少，忽略未使用的字段

```kotlin
val retrofit = Retrofit.Builder()
    .client(OkHttpClient())
    .baseUrl(BASE_URL)
    .addConverterFactory(JsonConverterFactory.create())
    .build()
```

### 2.配置Json使用

```kotlin
val retrofit = Retrofit.Builder()
    .client(OkHttpClient())
    .baseUrl(BASE_URL)
    .addConverterFactory(JsonConverterFactory.create {
        prettyPrint = true
        ignoreUnknownKeys = true
        coerceInputValues = true
        ...
    })
    .build()
```
