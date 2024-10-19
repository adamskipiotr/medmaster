package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import jakarta.persistence.*
import lombok.Builder
import lombok.EqualsAndHashCode

@Entity
@Table(name = "ingredient")
data class Ingredient(
    @Id
    @SequenceGenerator(name = "ingredient_id_sequence", sequenceName = "ingredient_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_id_sequence")
    var id: Long = 0,
    val name: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicament_id")  // Join column for medicament
    val medicament: Medicament? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    val parent: Ingredient? = null,
    @OneToMany(mappedBy = "parent", cascade = [CascadeType.ALL], orphanRemoval = true)
    val mutuallyExclusive: List<Ingredient> = mutableListOf(),
    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE], fetch = FetchType.LAZY)
    @JoinTable(
        name = "ingredient__prohibiting_country",
        joinColumns = [JoinColumn(name = "ingredient_id")],
        inverseJoinColumns = [JoinColumn(name = "country_id")]
    )
    val prohibitingCountries: List<Country> = mutableListOf()
)