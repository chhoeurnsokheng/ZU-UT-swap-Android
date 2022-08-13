package com.zillennium.utswap.bases.websocket

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

abstract class WSModel<T> : WS() {

    protected lateinit var type: Type

    companion object {
        private val GSON = Gson()
    }

    init {
        analysisType()
    }

    private fun analysisType() {
        val superclass = javaClass.genericSuperclass
        if (superclass is Class<*>) {
            throw RuntimeException("No generics found!")
        }
        val type = superclass as ParameterizedType
        this.type = type.actualTypeArguments[0]
    }

    override fun onMessage(text: String?) {
        Observable.just(text)
            .map(Func1 { s ->
                try {
                    return@Func1 GSON.fromJson<T>(s, type)
                } catch (e: JsonSyntaxException) {
                    return@Func1 GSON.fromJson<T>(
                        GSON.fromJson(
                            s,
                            String::class.java
                        ), type
                    )
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ t -> onMessage(t) }, { e -> onError(e) })

    }
    protected abstract fun onMessage(text: T?)

}