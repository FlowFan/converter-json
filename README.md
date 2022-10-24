# JsonConverterFactory

一个用于 Json 序列化的 `Converter.Factory`
，类似于 [GsonConverterFactory](https://github.com/square/retrofit/tree/master/retrofit-converters/gson) ， Retrofit 2
并没有提供 [Kotlin Serialization](https://github.com/Kotlin/kotlinx.serialization/) 的 Converter
库，所以封装了此 [JsonConverterFactory](https://github.com/fan1138612367/converter-json) 。

# 使用

### 添加依赖

> Project中：

```groovy
maven { url 'https://jitpack.io' }
```

> Module中：

```groovy
implementation 'com.github.fan1138612367:converter-json:1.4.1'
```

### 1.直接使用

> 默认配置：
> 1.如果Json字段是Null则使用数据类中定义的默认值
> 2.允许数据类中的字段比Json字段少

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
