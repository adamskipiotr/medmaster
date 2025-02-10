package com.pada.medmaster.domain.service.patient

import com.pada.medmaster.application.ports.out.medicament.GetMultipleMedicamentByIdPort
import com.pada.medmaster.domain.model.medicament.Medicament

class GetMultipleFakeMedicamentPort() : GetMultipleMedicamentByIdPort {

    private val medicaments = mutableMapOf(
        1L to Medicament(1L,"Name", "Producer", "Counteractions",
            mutableListOf(1L), mutableListOf())
    )



    override fun get(ids: List<Long?>): List<Medicament> {
        val foundMedicaments = ids.mapNotNull { medicaments[it] }

        if (foundMedicaments.size != ids.size) {
            throw NoSuchElementException("Some ingredients not found: ${ids - medicaments.keys}")
        }

        return foundMedicaments
    }

}
