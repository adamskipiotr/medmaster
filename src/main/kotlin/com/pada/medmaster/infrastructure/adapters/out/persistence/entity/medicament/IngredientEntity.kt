package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.medicament

import com.pada.medmaster.domain.model.medicament.Ingredient
import jakarta.persistence.*

@Entity
@Table(name = "ingredient")
class IngredientEntity {
    @Id
    @SequenceGenerator(name = "ingredient_id_sequence", sequenceName = "ingredient_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_id_sequence")
    var id: Long = 0

    lateinit var name: String

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "medicament_id")  // Join column for medicament
    var medicament: MedicamentEntity? = null

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "parent_id")
    var parent: IngredientEntity? = null

    @OneToMany(mappedBy = "parent", cascade = [CascadeType.ALL], orphanRemoval = true)
    lateinit var mutuallyExclusive: List<IngredientEntity>

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE], fetch = FetchType.LAZY)
    @JoinTable(
        name = "ingredient__prohibiting_country",
        joinColumns = [JoinColumn(name = "ingredient_id")],
        inverseJoinColumns = [JoinColumn(name = "country_id")]
    )
    var prohibitingCountries: MutableList<CountryEntity> = mutableListOf()


    fun asDomain(): Ingredient {
        return Ingredient(
            id,
            name,
            medicament?.asDomain(),
            null,
            mutableListOf(),
            prohibitingCountries.map { it.asDomain() }.toMutableList()
        )
    }
}


