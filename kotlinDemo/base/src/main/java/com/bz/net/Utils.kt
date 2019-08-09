package com.bz.net

import android.os.Looper
import android.util.Log

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.TypeReference
import com.alibaba.fastjson.parser.Feature
import com.google.gson.Gson

import java.io.File
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.ArrayList

import okhttp3.RequestBody

/**
 * copy  Copyright (C) 2007 The Guava Authors by from retrofit2
 *
 * @author Tamic(https://github.com/NeglectedByBoss)
 */
object Utils {

    private val TAG = Utils::class.java.simpleName

    fun <T> checkNotNull(`object`: T?, message: String): T {
        if (`object` == null) {
            throw NullPointerException(message)
        }
        return `object`
    }

    fun checkMain(): Boolean {
        return Thread.currentThread() === Looper.getMainLooper().thread
    }

    fun createJson(jsonString: String): RequestBody {
        checkNotNull(jsonString, "json not null!")
        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), jsonString)
    }

    /**
     * @param name
     * @return
     */
    fun createFile(name: String): RequestBody {
        checkNotNull(name, "name not null!")
        return RequestBody.create(okhttp3.MediaType.parse("multipart/form-data; charset=utf-8"), name)
    }

    /**
     * @param file
     * @return
     */
    fun createFile(file: File): RequestBody {
        checkNotNull(file, "file not null!")
        return RequestBody.create(okhttp3.MediaType.parse("multipart/form-data; charset=utf-8"), file)
    }

    /**
     * @param file
     * @return
     */
    fun createImage(file: File): RequestBody {
        checkNotNull(file, "file not null!")
        return RequestBody.create(okhttp3.MediaType.parse("image/jpg; charset=utf-8"), file)
    }


    /**
     * MethodHandler
     */
    fun MethodHandler(types: Array<Type>): List<Type> {
        Log.d(TAG, "types size: " + types.size)
        val needtypes = ArrayList<Type>()

        for (paramType in types) {
            if (paramType is ParameterizedType) {
                val parentypes = paramType.actualTypeArguments
                for (childtype in parentypes) {
                    needtypes.add(childtype)
                    if (childtype is ParameterizedType) {
                        val childtypes = childtype.actualTypeArguments
                        for (type in childtypes) {
                            needtypes.add(type)
                        }
                    }
                }
            }
        }
        return needtypes
    }


    fun <T> parseObject(jsonStr: String, entityClass: Class<T>): T? {
        var ret: T? = null

        try {
//            var gson=Gson()
//            ret =gson.fromJson(jsonStr,entityClass)
            ret = JSON.parseObject(jsonStr, entityClass)
        } catch (e: Exception) {
            Log.e("Novte", "parseObject-something Exception with:$e")
            e.printStackTrace()
        }

        return ret
    }

    fun <T> parseObject(jsonStr: String, type: Type): T {
        var obj: T? = null
        try {
            var gson=Gson()
            obj =gson.fromJson(jsonStr,type)
//            obj = JSON.parseObject<T>(jsonStr, type, Feature.AutoCloseSource)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return obj!!
    }


    fun <T> parseObject(jsonStr: String, tf: TypeReference<T>): T? {
        var obj: T? = null
        try {
            obj = JSON.parseObject(jsonStr, tf, Feature.AutoCloseSource)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return obj
    }

}
