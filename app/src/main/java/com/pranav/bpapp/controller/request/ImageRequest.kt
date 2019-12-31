package com.pranav.bpapp.controller.request

import android.content.Context
import com.pranav.bpapp.controller.ProcessResponceInterphase
import com.pranav.bpapp.controller.responce.Response
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class ImageRequest(
    context: Context,
    rhandler: ProcessResponceInterphase<Response>
) :
    AbstractRequest<Response>(context, rhandler) {

    fun uploadFeed(body: String, subject: String, file: File?) {
        val params = HashMap<String, RequestBody>()

        val rbBody = body.toRequestBody("multipart/form-data".toMediaTypeOrNull())
        params["content"] = rbBody

        val rbsubject = subject.toRequestBody("multipart/form-data".toMediaTypeOrNull())
        params["subject"] = rbsubject

        if (file != null) {
            val image = file!!.absoluteFile.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            val imagePart = MultipartBody.Part.createFormData("image", file!!.name, image)

            val call = appInterphase.postImagefunction(appToken, appJson, rbBody, rbsubject, imagePart)
            call.enqueue(this)
        } else {
            val call = appInterphase.postImagefunction(appToken, appJson, rbBody, rbsubject, null)
            call.enqueue(this)
        }
    }

}