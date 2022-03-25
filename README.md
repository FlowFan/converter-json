# JsonConverterFactory
一个用于Json的转换器，类似于GsonConverterFactory，Retrofit 2并没有提供用于Kotlin Serialization的ConverterFactory库，所以封装了此JsonConverterFactory

# 使用

### 添加依赖

> Project中：

```groovy
maven { url 'https://jitpack.io' }
```

> Module中：

```groovy
implementation 'com.github.fan1138612367:converter-json:1.3.2'
```

### 直接使用

```kotlin
val retrofit = Retrofit.Builder()
    .client(OkHttpClient())
    .baseUrl(BASE_URL)
    .addConverterFactory(JsonConverterFactory.create())
    .build()
```

### 配置Json

```kotlin
val retrofit = Retrofit.Builder()
    .client(OkHttpClient())
    .baseUrl(BASE_URL)
    .addConverterFactory(JsonConverterFactory.create(Json {
        prettyPrint = true
        ignoreUnknownKeys = true
        coerceInputValues = true
        ...
    }))
    .build()
```
