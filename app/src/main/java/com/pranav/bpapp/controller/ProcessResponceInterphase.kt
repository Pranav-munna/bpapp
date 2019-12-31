package com.pranav.bpapp.controller

interface ProcessResponceInterphase<T> {
        fun processResponce(responce: T)
        fun processResponceError(responce: Any?)
}