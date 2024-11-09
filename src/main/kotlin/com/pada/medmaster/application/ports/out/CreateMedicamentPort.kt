package com.pada.medmaster.application.ports.out

import com.pada.medmaster.domain.model.treatment.Medicament

interface CreateMedicamentPort {
    fun createMedicament(medicament: Medicament)

}