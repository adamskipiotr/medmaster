package com.pada.medmaster.application.ports.out

import com.pada.medmaster.domain.model.medicament.Medicament

interface CreateMedicamentPort {
    fun createMedicament(medicament: Medicament)

}