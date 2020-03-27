package com.bruce.sx.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder

class GsonUtil private constructor() {
    private val mGson: Gson
    init {
        mGson = GsonBuilder()
            .setLenient()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
            .serializeNulls()
            .create()
    }

    companion object {
        private val instance: GsonUtil
        init {
            instance = GsonUtil()
        }

        val gson: Gson
            get() = instance.mGson

        fun JSONToObject(json: String, beanClass: Class<*>): Any {
            val gson = Gson()
            return gson.fromJson(json, beanClass)
        }
    }

}
