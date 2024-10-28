package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import com.pada.medmaster.domain.model.treatment.Country

import jakarta.persistence.*

@Entity
@Table(name = "country")
class CountryEntity(
    @Id
    @SequenceGenerator(name = "country_id_sequence", sequenceName = "country_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_id_sequence")
    var id: Long = 0,
    val name: String,
    @ManyToMany(
        mappedBy = "prohibitingCountries",
        fetch = FetchType.LAZY
    )
    val prohibitedIngredients: Set<IngredientEntity>,
) {

    fun asDomain(): Country {
        return Country(
            id, name, prohibitedIngredients.map { it.asDomain() }.toSet()
        )
    }

    companion object {
        fun of(country: Country): CountryEntity {
           return CountryEntity(
                country.id ?: 0,
                country.name,
               country.prohibitedIngredients?.map { IngredientEntity.of(it) }?.toSet() ?: emptySet()
            )
        }
    }
}
