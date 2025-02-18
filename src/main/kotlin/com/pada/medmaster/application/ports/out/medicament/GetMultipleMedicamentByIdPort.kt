package com.pada.medmaster.application.ports.out.medicament

import com.pada.medmaster.domain.model.medicament.Medicament

fun interface GetMultipleMedicamentByIdPort {
    fun get(ids: List<Long?>) : List<Medicament>
}