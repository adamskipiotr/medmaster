package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.medicament

import com.pada.medmaster.domain.model.ingredient.Country
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.ingredient.IngredientEntity

import jakarta.persistence.*

@Entity
@Table(schema = "ingredient_schema", name = "country")
class CountryEntity {
    @Id
    @SequenceGenerator(name = "country_id_sequence", sequenceName = "country_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_id_sequence")
    var id: Long = 0
    lateinit var name: String

    @ManyToMany(
        mappedBy = "prohibitingCountries",
        fetch = FetchType.LAZY
    )
    lateinit var prohibitedIngredients: Set<IngredientEntity>

    fun asDomain(): Country {
        return Country(
            id, name, prohibitedIngredients.map { it.asDomain() }.toSet()
        )
    }
}
