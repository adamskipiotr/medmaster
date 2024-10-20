package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import com.pada.medmaster.domain.model.treatment.Medicament
import jakarta.persistence.*

@Entity
@Table(name = "medicament")
class MedicamentEntity(
    @Id
    @SequenceGenerator(name = "medicament_id_sequence", sequenceName = "medicament_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicament_id_sequence")
    var id: Long = 0,
    val name: String,
    ) {
    @OneToMany(
        mappedBy = "medicament", cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY, orphanRemoval = true
    )
    val ingredients: List<IngredientEntity> = mutableListOf()  // Initialize to empty list

    fun asDomain(): Medicament {
        return Medicament(
            id = id,
            name = name,
            ingredients = ingredients.map { it.asDomain() }
        )
    }
}

