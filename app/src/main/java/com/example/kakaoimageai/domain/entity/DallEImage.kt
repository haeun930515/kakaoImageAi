package com.example.kakaoimageai.domain.entity

import com.google.gson.annotations.SerializedName

data class DallEImage(
    @SerializedName("created")
    var created: String = "",
    @SerializedName("data")
    var data: MutableList<Data> = mutableListOf()

    ) {
    data class Data(
        @SerializedName("url")
        var url: String = ""
    )
}
