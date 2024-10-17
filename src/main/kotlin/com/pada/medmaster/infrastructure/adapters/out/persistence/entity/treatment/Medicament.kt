package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import jakarta.persistence.*

@Entity
@Table(name = "medicament")
class Medicament(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
    private val name: String,
    private val ingredients: List<Ingredient>
) {

}