package com.dab1e.retrofitrx

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("user")var user:String,
                @SerializedName("age") var age:Int,
                @SerializedName("firstname") var firstname:String,
                @SerializedName("lastname") var lastname:String
                )
data class Tivi(@SerializedName("TV") var tv:String,
                @SerializedName("kho") var kho:Int,
                @SerializedName("soluong") var soluong:Int)

data class Dog(
                @SerializedName("fileSizeBytes") var fileSize:Long,
                @SerializedName("url") var url:String
)