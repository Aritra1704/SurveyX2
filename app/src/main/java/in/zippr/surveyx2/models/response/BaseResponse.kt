package `in`.zippr.surveyx2.models.response

import com.google.gson.JsonObject

open class BaseResponse(val ok: String, var response: JsonObject, var error: JsonObject) {
//    internal var ok: String
//
//    internal var response: JsonObject
//    internal var error: JsonObject
//
//    fun getOk(): String {
//        return ok
//    }
//
//    fun setOk(ok: String) {
//        this.ok = ok
//    }
//
//    fun getResponse(): Any {
//        return response
//    }
//
//    fun setResponse(response: JsonObject) {
//        this.response = response
//    }
//
//    fun getError(): JsonObject {
//        return error
//    }
//
//    fun setError(error: JsonObject) {
//        this.error = error
//    }

    override fun toString(): String {
        return "BaseResponse{" +
                "ok='" + ok + '\''.toString() +
                ", response=" + response +
                ", error=" + error +
                '}'.toString()
    }
}