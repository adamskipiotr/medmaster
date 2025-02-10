package com.pada.medmaster.domain.service.patient

import com.pada.medmaster.application.ports.out.medicament.GetMedicamentPort
import com.pada.medmaster.domain.model.medicament.Medicament

class GetFakeMedicamentPort() : GetMedicamentPort {

    private val medicaments = mutableMapOf(
        1L to Medicament(1L,"Name", "Producer",
            "Counteractions", mutableListOf(1L), mutableListOf())
    )

    override fun get(id: Long): Medicament {
        return medicaments[id] ?: throw NoSuchElementException("Medicament not found")
    }
}
