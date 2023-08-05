package com.erp.sf.model

import java.io.Serializable

data class ApiResponse<T>(val code:Int , val data:T?,val message:String?):Serializable{
    companion object{
        private fun <T> failed(code:Int,message:String):ApiResponse<T>{
            return ApiResponse(code,null,message)
        }
        fun <T> success(data:T,message:String):ApiResponse<T>{
            return ApiResponse(200,data,null)
        }
        fun <T> tokenFailed (message: String):ApiResponse<T>{
            return failed(401,message)
        }
        fun <T> businessFailed (message: String):ApiResponse<T>{
            return failed(501,message)
        }
        fun <T> validateFailed (message: String):ApiResponse<T>{
            return failed(400,message)
        }


    }
}
