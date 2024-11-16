package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.medicament

import com.pada.medmaster.domain.model.pharmacy.Pharmacy
import jakarta.persistence.*


//Aggregate Root: Medicament
@Entity
@Table(name = "pharmacy")
class PharmacyEntity(
    @Id
    @SequenceGenerator(name = "pharmacy_id_sequence", sequenceName = "pharmacy_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pharmacy_id_sequence")
    var id: Long = 0,
    var name: String = "", // to fix: Without default value - "No default constructor for entity" exception
    var voivodeship: String = "",
    var district: String = "",
    var community: String = "",
    var location: String = "",
    var street: String = "",
    var buildingNumber: String = "",
    var apartmentNumber: String = "",
    var zipCode: String = "",
) {
    fun asDomain(): Pharmacy {
        return Pharmacy(
            id,
            name,
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

