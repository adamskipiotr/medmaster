package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.medicament

import com.pada.medmaster.domain.model.medicament.Pharmacy
import jakarta.persistence.*


//Aggregate Root: Medicament
@Entity
@Table(schema = "medicament_schema", name = "pharmacy")
class PharmacyEntity(
    @Id
    @SequenceGenerator(
        schema = "medicament_schema",
        name = "pharmacy_id_sequence",
        sequenceName = "pharmacy_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pharmacy_id_sequence")
    var id: Long = 0,
    var name: String = "", // to fix: Without default value - "No default constructor for entity" exception

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    var address: PharmacyAddressEntity? = null

) {
    fun asDomain(): Pharmacy {
        return Pharmacy(
            id,
            name,
            address?.asDomain()
        )
    }
}

