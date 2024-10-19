package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import jakarta.persistence.*
import lombok.Builder
import lombok.EqualsAndHashCode

@Entity
class Country(
    @Id
    @SequenceGenerator(name = "country_id_sequence", sequenceName = "country_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_id_sequence")
    var id: Long = 0,
    val name: String,
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ManyToMany(
        mappedBy = "prohibitingCountries",
        fetch = FetchType.LAZY
    )
    val prohibitedIngredients: Set<Ingredient?>? = HashSet<Ingredient?>()
) {

}
