package com.pada.medmaster.domain.service.patient.stubs

import com.pada.medmaster.application.ports.out.medicament.GetMedicamentPort
import com.pada.medmaster.domain.model.medicament.Medicament
import com.pada.medmaster.domain.model.medicament.Pharmacy
import com.pada.medmaster.domain.model.medicament.PharmacyAddress

class GetMedicamentPortStub() : GetMedicamentPort {

    private val pharmacyAddress = PharmacyAddress(
        1L, "Voivodeship", "District", "Community",
        "Location", "Street", "20A", "4C", "12345"
    )
    private val pharmacy = Pharmacy(1L, "Pharmacy", pharmacyAddress)
    private val medicaments = mutableMapOf(
        1L to Medicament(
            1L, "Medicament 1", "Producer",
            "Counteractions", mutableListOf(1L), mutableListOf(pharmacy)
        ),
        2L to Medicament(
            2L, "Medicament 2", "Producer",
            "Counteractions", mutableListOf(2L), mutableListOf(pharmacy)
        ),
        3L to Medicament(
            3L, "Medicament 3", "Producer",
            "Counteractions", mutableListOf(3L), mutableListOf(pharmacy)
        ),
        4L to Medicament(
            4L, "Medicament 4", "Producer",
            "Counteractions", mutableListOf(4L), mutableListOf(pharmacy)
        )
    )

    override fun get(id: Long): Medicament {
        return medicaments[id] ?: throw NoSuchElementException("Medicament not found")
    }
}
