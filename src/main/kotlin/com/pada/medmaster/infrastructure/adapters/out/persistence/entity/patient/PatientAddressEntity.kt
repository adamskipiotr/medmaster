package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient

import com.pada.medmaster.domain.model.patient.PatientAddress
import jakarta.persistence.*


//Aggregate Root: Medicament
@Entity
@Table(schema = "patient_schema", name = "patient_address")
class PatientAddressEntity(
    @Id
    @SequenceGenerator(
        schema = "patient_schema",
        name = "patient_address_id_sequence",
        sequenceName = "patient_address_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_address_id_sequence")
    var id: Long = 0,
    var country: String = "",
    var voivodeship: String = "",
    var district: String = "",
    var community: String = "",
    var location: String = "",
    var street: String = "",
    var buildingNumber: String = "",
    var apartmentNumber: String = "",
    var zipCode: String = "",
) {
    fun asDomain(): PatientAddress {
        return PatientAddress(
            id,
            country,
            voivodeship,
            district,
            community,
            location,
            street,
            buildingNumber,
            apartmentNumber,
            zipCode
        )
    }
}

