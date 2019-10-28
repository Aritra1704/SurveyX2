package `in`.zippr.surveyx2.common

import com.arpaul.utilitieslib.LogUtils

class MyLogger {

    lateinit var logger: LogUtils
    constructor(isLogEnabled: Boolean) {
        logger = LogUtils(isLogEnabled)
    }

    fun d(tag: String, message: String) {
        LogUtils.debugLog(tag, message)
//        logger.let {
//            it.debugLog(message)
//        }
    }
}