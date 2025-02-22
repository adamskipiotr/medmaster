package com.pada.medmaster.domain.service.patient.stubs

import com.pada.medmaster.application.ports.out.medicament.GetMultipleMedicamentByIdPort
import com.pada.medmaster.domain.model.medicament.Medicament

class GetMultipleMedicamentPortStub() : GetMultipleMedicamentByIdPort {

    private val medicaments = mutableMapOf(
        1L to Medicament(
            1L, "Medicament 1", "Producer", "Counteractions",
            mutableListOf(1L), mutableListOf()
        ),
        2L to Medicament(
            2L, "Medicament 2", "Producer", "Counteractions",
            mutableListOf(2L), mutableListOf()
        ),
        3L to Medicament(
            3L, "Medicament 3", "Producer", "Counteractions",
            mutableListOf(3L), mutableListOf()
        ),
        4L to Medicament(
            4L, "Medicament 4", "Producer", "Counteractions",
            mutableListOf(4L), mutableListOf()
        )
    )

    override fun get(ids: List<Long?>): List<Medicament> {
        val foundMedicaments = ids.mapNotNull { medicaments[it] }

        if (foundMedicaments.size != ids.size) {
            throw NoSuchElementException("Some ingredients not found: ${ids - medicaments.keys}")
        }
        return foundMedicaments
    }
}
