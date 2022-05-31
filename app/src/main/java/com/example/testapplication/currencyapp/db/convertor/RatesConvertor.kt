package com.example.testapplication.currencyapp.db.convertor

import com.example.testapplication.currencyapp.db.entity.RatesEntity
import com.example.testapplication.currencyapp.models.Rates

object RatesConvertor {

    fun toDatabase(rates: Rates): RatesEntity =
        RatesEntity(
            USD = rates.USD,
            AUD = rates.AUD,
            CAD = rates.CAD,
            PLN = rates.PLN,
            MXN = rates.MXN,

            )

    fun fromDatabase(entity: RatesEntity): Rates = Rates(
        USD = entity.USD,
        AUD = entity.AUD,
        CAD = entity.CAD,
        PLN = entity.PLN,
        MXN = entity.MXN,
    )
}