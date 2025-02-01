package com.pada.medmaster.application.ports.out.medicament

import com.pada.medmaster.domain.model.medicament.Medicament

interface GetMultipleMedicamentByIdPort {
    fun get(ids: List<Long?>) : List<Medicament>

}