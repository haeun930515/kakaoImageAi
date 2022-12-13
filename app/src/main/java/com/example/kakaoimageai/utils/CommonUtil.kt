package com.example.kakaoimageai.utils

import com.google.firebase.database.DatabaseReference

object CommonUtil {

    const val FB_DB_URL = "https://kakaoimageai-default-rtdb.firebaseio.com"
    const val FB_DB_USERS = "users"
    const val FB_DB_USER_CHILD_ID = "id"
    lateinit var FB_REF: DatabaseReference
}