package com.example.equimpcontrol.login

class LoginModel {
    fun login(login : String?, password : String?) : Boolean {
        val regex = Regex("[a-zA-Z0-9]+")
        return (login != null && password != null) && password.length > 5 && regex.matches(password)
    }
}