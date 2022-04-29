package com.example.testapplication.db.convertor

import com.example.testapplication.db.entity.RatesEntity
import com.example.testapplication.models.Rates

object RatesConvertor {

    fun toDatabase(rates: Rates): RatesEntity =
        RatesEntity(
            USD = rates.USD,
            AUD = rates.AUD,
            CAD = rates.CAD,
            PLN = rates.PLN,
            MXN = rates.MXN,
            id = rates.id!!

        )

    fun fromDatabase(entity: RatesEntity): Rates = Rates(
        USD = entity.USD,
        AUD = entity.AUD,
        CAD = entity.CAD,
        PLN = entity.PLN,
        MXN = entity.MXN,
        id = entity.id
    )
}