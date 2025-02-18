package com.pada.medmaster.application.ports.out.medicament

import com.pada.medmaster.domain.model.medicament.Medicament

fun interface GetMedicamentPort {
    fun get(id: Long): Medicament
}