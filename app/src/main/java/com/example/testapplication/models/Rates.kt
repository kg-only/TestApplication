package com.example.testapplication.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Rates (

    @SerializedName("USD" ) var USD : Double? = null,
    @SerializedName("AUD" ) var AUD : Double? = null,
    @SerializedName("CAD" ) var CAD : Double? = null,
    @SerializedName("PLN" ) var PLN : Double? = null,
    @SerializedName("MXN" ) var MXN : Double? = null

):Parcelable