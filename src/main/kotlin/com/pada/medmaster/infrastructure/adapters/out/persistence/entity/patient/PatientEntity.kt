package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient

import com.pada.medmaster.domain.model.patient.Patient
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.ingredient.IngredientEntity
import jakarta.persistence.*
import java.time.LocalDate

// Aggregate Root: Patient
@Entity
@Table(schema = "patient_schema", name = "patient")
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
        schema = "patient_schema",
        name = "patient_special_health_conditions",
        joinColumns = [JoinColumn(name = "patient_id")]
    )
    @Enumerated(EnumType.STRING)
    lateinit var specialHealthConditions: MutableList<SpecialHealthConditions>

    @ElementCollection
    @CollectionTable(
        schema = "shared_schema",
        name = "patient__treatments",
        joinColumns = [JoinColumn(name = "patient_id")]
    )
    @Column(name = "treatment_id")
    var treatmentsIds: MutableList<Long> = mutableListOf()

    @ManyToMany(cascade = [CascadeType.PERSIST, CascadeType.MERGE], fetch = FetchType.LAZY)
    @JoinTable(
        schema = "shared_schema",
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

    fun addTreatment(treatmentId: Long) {
        treatmentsIds.add(treatmentId)
    }
}
