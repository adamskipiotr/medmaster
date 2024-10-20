package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import com.pada.medmaster.domain.model.treatment.Country
import jakarta.persistence.*
import lombok.Builder
import lombok.EqualsAndHashCode

@Entity
class CountryEntity(
    @Id
    @SequenceGenerator(name = "country_id_sequence", sequenceName = "country_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_id_sequence")
    var id: Long = 0,
    val name: String,
) {
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ManyToMany(
        mappedBy = "prohibitingCountries",
        fetch = FetchType.LAZY
    )
    val prohibitedIngredients: Set<IngredientEntity> = emptySet()

    fun asDomain(): Country {
        return Country(
            id = id,
            name = name,
            prohibitedIngredients = prohibitedIngredients.map { it.asDomain() }.toSet()
        )
    }
}

