package com.erp.sf.model

import java.io.Serializable

data class SystemResponse(val code: Int, val mapper: Map<String, String>, val message: String?) : Serializable {
    companion object {
        private fun message(
            code: Int,
            message: String
        ): SystemResponse {
            return SystemResponse(code, emptyMap(), message)
        }

        fun tokenFailed(message: String): SystemResponse {
            return SystemResponse.message(401, message)
        }
        @JvmStatic
        fun dataFailed(message: String): SystemResponse {
            return SystemResponse.message(501, message)
        }
    }
}
