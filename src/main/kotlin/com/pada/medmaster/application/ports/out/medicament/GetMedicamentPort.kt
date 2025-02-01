package com.pada.medmaster.application.ports.out.medicament

import com.pada.medmaster.domain.model.medicament.Medicament

interface GetMedicamentPort {
    fun get(id: Long): Medicament

}