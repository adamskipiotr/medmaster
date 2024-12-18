package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient

import com.pada.medmaster.domain.model.medicament.Medicament
import com.pada.medmaster.domain.model.patient.Patient
import com.pada.medmaster.domain.model.treatment.Treatment
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.ingredient.IngredientEntity
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.medicament.PharmacyEntity
import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

// Aggregate Root: Patient
@Entity
@Table(name = "patient")
class PatientEntity {

    lateinit var name: String
    lateinit var lastName: String
    lateinit var birthDate: LocalDate
    @Enumerated(EnumType.STRING)  // Use @Enumerated to map the enum
    lateinit var gender: Gender

    @Id
    @SequenceGenerator(name = "patient_id_sequence", sequenceName = "patient_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_id_sequence")
    var id: Long = 0

    @ElementCollection(targetClass = SpecialHealthConditions::class)
    @CollectionTable(
        name = "patient_special_health_conditions",
        joinColumns = [JoinColumn(name = "patient_id")]
    )
    @Enumerated(EnumType.STRING)
    lateinit var specialHealthConditions: MutableList<SpecialHealthConditions>

    @ElementCollection
    @CollectionTable(
        name = "patient__treatments",
        joinColumns = [JoinColumn(name = "patient_id")]
    )
    @Column(name = "treatment_id")
    var treatmentsIds: MutableList<Long> = mutableListOf()

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE], fetch = FetchType.LAZY)
    @JoinTable(
        name = "patient__allergic_ingredients",
        joinColumns = [JoinColumn(name = "patient_id")],
        inverseJoinColumns = [JoinColumn(name = "allergic_ingredient_id")]
    )
    var allergicIngredients: MutableList<IngredientEntity> = mutableListOf()

    fun asDomain() = Patient(
        id,
        name,
        lastName,
        birthDate,
        specialHealthConditions,
        gender
    )
}
