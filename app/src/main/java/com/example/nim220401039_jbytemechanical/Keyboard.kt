package com.example.nim220401039_jbytemechanical

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize

data class Keyboard(
    val product_name: String,
    val product_price: String,
    val product_image: Int
) : Parcelable
