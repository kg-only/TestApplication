package com.example.testapplication.models

import com.google.gson.annotations.SerializedName

data class Rates (

    @SerializedName("USD" ) var USD : Double? = null,
    @SerializedName("AUD" ) var AUD : Double? = null,
    @SerializedName("CAD" ) var CAD : Double? = null,
    @SerializedName("PLN" ) var PLN : Double? = null,
    @SerializedName("MXN" ) var MXN : Double? = null

)