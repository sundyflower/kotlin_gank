package com.bz.common

import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface Api {
    companion object{
         val  baseUrl="http://gank.io/api/"
    }



    @POST()
    fun postJson(
            @Url url: String,
            @Body jsonBody: RequestBody): Observable<ResponseBody>



    @POST()
    @FormUrlEncoded
    fun executePost(
            @Url url: String,
            @FieldMap maps: Map<String, Any>): Observable<ResponseBody>


    @GET()
    fun executeGet(
            @Url url: String,
            @QueryMap maps: Map<String, Any>): Observable<ResponseBody>

    @GET("data/{model}/{number}/{page}")
    fun executeGetList(@Path("model")mode: String,@Path("number")number: Int,@Path("page") page:Int): Observable<ResponseBody>

    @Streaming
    @GET()
    fun downloadFile(@Url fileUrl: String): Observable<ResponseBody>

    @Multipart
    @POST()
    fun upload(@Url fileUrl: String, @Part imgs: MultipartBody.Part): Observable<ResponseBody>
}