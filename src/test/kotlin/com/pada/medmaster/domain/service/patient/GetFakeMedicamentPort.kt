package com.pada.medmaster.domain.service.patient

import com.pada.medmaster.application.ports.out.medicament.GetMedicamentPort
import com.pada.medmaster.domain.model.medicament.Medicament
import com.pada.medmaster.domain.model.medicament.Pharmacy
import com.pada.medmaster.domain.model.medicament.PharmacyAddress

class GetFakeMedicamentPort() : GetMedicamentPort {

    private val pharmacyAddress = PharmacyAddress(
        1L, "Voivodeship", "District", "Community",
        "Location", "Street", "20A", "4C", "12345"
    )
    private val pharmacy = Pharmacy(1L, "Pharmacy", pharmacyAddress)
    private val medicaments = mutableMapOf(
        1L to Medicament(
            1L, "Name", "Producer",
            "Counteractions", mutableListOf(1L), mutableListOf(pharmacy)
        ),
        2L to Medicament(
            2L, "Name", "Producer",
            "Counteractions", mutableListOf(2L), mutableListOf(pharmacy)
        )
    )

    override fun get(id: Long): Medicament {
        return medicaments[id] ?: throw NoSuchElementException("Medicament not found")
    }
}
