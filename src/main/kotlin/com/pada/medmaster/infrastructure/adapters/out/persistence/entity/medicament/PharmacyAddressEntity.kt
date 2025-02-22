package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.medicament

import com.pada.medmaster.domain.model.medicament.PharmacyAddress
import jakarta.persistence.*


//Aggregate Root: Medicament
@Entity
@Table(schema = "medicament_schema", name = "pharmacy_address")
class PharmacyAddressEntity(
    @Id
    @SequenceGenerator(
        schema = "medicament_schema",
        name = "pharmacy_address_id_sequence",
        sequenceName = "pharmacy_address_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pharmacy_address_id_sequence")
    var id: Long = 0,
    var voivodeship: String = "",
    var district: String = "",
    var community: String = "",
    var location: String = "",
    var street: String = "",
    var buildingNumber: String = "",
    var apartmentNumber: String = "",
    var zipCode: String = "",
) {
    fun asDomain(): PharmacyAddress {
        return PharmacyAddress(
            id,
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

