package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.ingredient

import com.pada.medmaster.domain.model.ingredient.Ingredient
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.medicament.CountryEntity
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

// Aggregate Root: Ingredient
@Entity
@Table(schema = "ingredient_schema", name = "ingredient")
class IngredientEntity {
    @Id
    @SequenceGenerator(
        schema = "ingredient_schema",
        name = "ingredient_id_sequence",
        sequenceName = "ingredient_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_id_sequence")
    var id: Long = 0

    lateinit var name: String

    var medicamentId: Long? = null

    @ManyToMany
    @JoinTable(
        schema = "ingredient_schema",
        name = "ingredient_incompatibilities",
        joinColumns = [JoinColumn(name = "ingredient_id")],
        inverseJoinColumns = [JoinColumn(name = "incompatible_ingredient_id")]
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    val incompatibleIngredients: MutableSet<IngredientEntity> = mutableSetOf()

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE], fetch = FetchType.LAZY)
    @JoinTable(
        schema = "ingredient_schema",
        name = "ingredient__prohibiting_country",
        joinColumns = [JoinColumn(name = "ingredient_id")],
        inverseJoinColumns = [JoinColumn(name = "country_id")]
    )
    var prohibitingCountries: MutableList<CountryEntity> = mutableListOf()


    fun asDomain(): Ingredient {
        return Ingredient(
            id,
            name,
            null, // changed from relation to ID - Ingredient as separate Aggregate Root
            mutableListOf(),
            prohibitingCountries.map { it.asDomain() }.toMutableList()
        )
    }
}


