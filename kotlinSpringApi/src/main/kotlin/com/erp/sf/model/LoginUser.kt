package com.erp.sf.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.io.Serializable

data class LoginUser(
    var employee: Employee,
    var authorities : List<String>,
    var simpleAuthorities: List<SimpleGrantedAuthority>
):Serializable,UserDetails {
    constructor(employee:Employee,authorities:List<String>):this(employee,authorities, emptyList())
    override fun getAuthorities(): Collection< GrantedAuthority> {
        simpleAuthorities=authorities.stream()
            .map { SimpleGrantedAuthority(it) }
            .toList()
        return simpleAuthorities
    }

    override fun getPassword(): String {
        return employee.password
    }

    override fun getUsername(): String {
        return employee.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
