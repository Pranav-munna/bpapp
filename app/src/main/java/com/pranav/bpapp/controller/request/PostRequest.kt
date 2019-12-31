package com.pranav.bpapp.controller.request

import android.content.Context
import com.pranav.bpapp.controller.ProcessResponceInterphase
import com.pranav.bpapp.controller.responce.Response

class PostRequest (con: Context, rhandler: ProcessResponceInterphase<Response>) :
    AbstractRequest<Response>(con, rhandler) {

    fun loginVerify(email: String) {
        val call = appInterphase.postfunction(appJson, email)
        call.enqueue(this)
    }

}