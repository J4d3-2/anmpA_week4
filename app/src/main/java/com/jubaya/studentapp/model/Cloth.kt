package com.jubaya.studentapp.model

import com.google.gson.annotations.SerializedName

data class Cloth(
    var id:String?,
    var type:String?,
    var brand:Brand?,
    var sizes:Array<String>?,
    var color:String?,
    @SerializedName("images")
    var photo_url:String?
)
