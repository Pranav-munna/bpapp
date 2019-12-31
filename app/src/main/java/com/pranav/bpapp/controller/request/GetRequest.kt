package com.pranav.bpapp.controller.request

import android.content.Context
import com.pranav.bpapp.controller.ProcessResponceInterphase
import com.pranav.bpapp.controller.responce.Response

class GetRequest(con: Context, rhandler: ProcessResponceInterphase<Response>) :
    AbstractRequest<Response>(con, rhandler) {

    fun feeds(search: String,page:Int) {
        val call = appInterphase
            .getfunction(appToken,appJson,search,page)
        call.enqueue(this)
    }


}