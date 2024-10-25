package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import com.pada.medmaster.domain.model.treatment.Medicament
import jakarta.persistence.*

@Entity
@Table(name = "medicament")
class MedicamentEntity(                 // TODO: why class should be open for Hibernate - for extending by Proxy
    @Id
    @SequenceGenerator(name = "medicament_id_sequence", sequenceName = "medicament_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicament_id_sequence")
    var id: Long = 0,
    val name: String,
    @OneToMany(                                                 // TODO: Move them here but dont use data class with JPA
        mappedBy = "medicament", cascade = [CascadeType.ALL],   // TODO: Or override equals and hashcode by yourself
        fetch = FetchType.LAZY, orphanRemoval = true
    )
    val ingredients: List<IngredientEntity>  // Initialize to empty list

) {

    fun asDomain(): Medicament {
        return Medicament(
            id, name,
            ingredients.map { it.asDomain() }
        )
    }

    companion object {
        fun of(medicament: Medicament): MedicamentEntity {
            return MedicamentEntity(
                medicament.id ?: 0,
                medicament.name,
                medicament.ingredients!!.map { IngredientEntity.of(it) } // todo avoid !!
            )
        }
    }
}

