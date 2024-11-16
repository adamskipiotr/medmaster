package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.medicament

import com.pada.medmaster.domain.model.medicament.Medicament
import jakarta.persistence.*


//Aggregate Root: Medicament
@Entity
@Table(name = "medicament")
class MedicamentEntity(                 // TODO: why class should be open for Hibernate - for extending by Proxy
    @Id
    @SequenceGenerator(name = "medicament_id_sequence", sequenceName = "medicament_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicament_id_sequence")
    var id: Long = 0,
    var name: String = "", // to fix: Without default value - "No default constructor for entity" exception
    var producer: String = "",
    var overdoseCounteractions: String = "",
    @OneToMany(
        mappedBy = "medicament", cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY, orphanRemoval = true
    )
    var ingredients: MutableList<IngredientEntity> = mutableListOf(),
    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE], fetch = FetchType.LAZY)
    @JoinTable(
        name = "medicament__pharmacies",
        joinColumns = [JoinColumn(name = "medicament_id")],
        inverseJoinColumns = [JoinColumn(name = "pharmacy_id")]
    )
    var pharmacies: MutableList<PharmacyEntity> = mutableListOf()
) {
    fun asDomain(): Medicament {
        return Medicament(
            id,
            name,
            producer,
            overdoseCounteractions
        )
    }
}

