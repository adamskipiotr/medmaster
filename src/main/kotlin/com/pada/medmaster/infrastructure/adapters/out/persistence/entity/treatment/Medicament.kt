package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import jakarta.persistence.*
import lombok.Builder

@Entity
@Table(name = "medicament")
class Medicament(
    @Id
    @SequenceGenerator(name = "medicament_id_sequence", sequenceName = "medicament_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicament_id_sequence")
    var id: Long = 0,
    private val name: String,
    @OneToMany(mappedBy = "medicament", cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY, orphanRemoval = true)
    private val ingredients: List<Ingredient> = mutableListOf()  // Initialize to empty list
)