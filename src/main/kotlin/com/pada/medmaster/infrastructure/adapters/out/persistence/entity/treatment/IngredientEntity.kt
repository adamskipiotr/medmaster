package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import com.pada.medmaster.domain.model.treatment.Ingredient
import jakarta.persistence.*

@Entity
@Table(name = "ingredient")
class IngredientEntity(
    @Id
    @SequenceGenerator(name = "ingredient_id_sequence", sequenceName = "ingredient_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_id_sequence")
    var id: Long = 0,
    val name: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicament_id")  // Join column for medicament
    val medicament: MedicamentEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    val parent: IngredientEntity?,

    @OneToMany(mappedBy = "parent", cascade = [CascadeType.ALL], orphanRemoval = true)
    val mutuallyExclusive: List<IngredientEntity>,

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE], fetch = FetchType.LAZY)
    @JoinTable(
        name = "ingredient__prohibiting_country",
        joinColumns = [JoinColumn(name = "ingredient_id")],
        inverseJoinColumns = [JoinColumn(name = "country_id")]
    )
    var prohibitingCountries: List<CountryEntity> = emptyList()
) {


    fun asDomain(): Ingredient {
        return Ingredient(
            id, name, medicament.asDomain(),
            null, emptyList(), prohibitingCountries.map { it.asDomain() }
        )
    }

    companion object {
        fun of(ingredient: Ingredient): IngredientEntity {
            return IngredientEntity(
                ingredient.id ?: 0,
                ingredient.name,
                MedicamentEntity.of(ingredient.medicament),
                null, // TODO fix null
                emptyList(),
                ingredient.prohibitingCountries.map { CountryEntity.of(it) },
            )
        }
    }
}

