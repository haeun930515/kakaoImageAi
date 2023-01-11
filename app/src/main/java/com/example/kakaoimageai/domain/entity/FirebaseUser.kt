package com.example.kakaoimageai.domain.entity

data class FirebaseUser (
            var name: String? = "",
            var images: MutableList<String>?
        )