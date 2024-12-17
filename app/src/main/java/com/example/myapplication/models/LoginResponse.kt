package com.example.myapplication.models

data class LoginResponse(
    val id: Int,
    val username: String,
    val is_admin: Boolean,
    val email: String,
    val first_name: String,
    val last_name: String,
    val expiry_date: String,
    val subscription_type: String,
    val stream_name: String,
    val stream_channel_name: String?,
    val profile_pic: String?,
    val full_name: String,
    val college_name: String?,
    val district: String?,
    val section: String?,
    val phone_number: String,
    val is_college_student: Boolean,
    val grade: Int,
    val district_name: String?,
    val is_email_verified: Boolean,
    val token: String,
    val jwt_token: String,
    val local_phone_number: String
)

data class LoginState(
    var status: LoginStatus = LoginStatus.Initial,
    var response: LoginResponse?,
)

enum class LoginStatus { Initial, Loading, Success, Failure }