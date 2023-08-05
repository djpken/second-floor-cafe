package com.erp.sf.service.security

import com.erp.sf.model.Employee

interface LoginService{
    fun login(employee: Employee):Map<String, Any>
    fun logout() :Map<String, Any>
    fun register(employee: Employee):Map<String, Any>

}