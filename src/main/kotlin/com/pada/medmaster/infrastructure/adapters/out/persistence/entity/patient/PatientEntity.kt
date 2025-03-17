package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient

import com.pada.medmaster.domain.model.patient.Patient
import com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.of
import jakarta.persistence.*
import java.time.LocalDate

// Aggregate Root: Patient
// Relations with other Aggregates - by Id, no Reference (both entities and domain): https://medium.com/@aforank/domain-driven-design-aggregates-in-practice-bcced7d21ae5
@Entity
@Table(schema = "patient_schema", name = "patient")
class PatientEntity {

    lateinit var name: String
    lateinit var lastName: String
    lateinit var birthDate: LocalDate

    @Enumerated(EnumType.STRING)  // Use @Enumerated to map the enum
    lateinit var gender: Gender

    @Id
    @SequenceGenerator(
        schema = "patient_schema",
        name = "patient_id_sequence",
        sequenceName = "patient_id_seq",
        allocationSize = 1
    )
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

    @OneToMany(
        mappedBy = "patient", cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER, orphanRemoval = true
    )
    var treatments: MutableList<TreatmentEntity> = mutableListOf()

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    var address: PatientAddressEntity? = null

    @ElementCollection
    @CollectionTable(
        schema = "shared_schema",
        name = "patient__allergic_ingredients",
        joinColumns = [JoinColumn(name = "patient_id")]
    )
    @Column(name = "allergic_ingredient_id")
    var allergicIngredients: MutableList<Long> = mutableListOf()

    fun asDomain() = Patient(
        id = id,
        name = name,
        lastName = lastName,
        birthDate = birthDate,
        specialHealthConditions = specialHealthConditions,
        gender = gender,
        allergicIngredients = allergicIngredients,
        treatments = treatments.map { it.asDomain(this.asDomainWithoutTreatments()) }
            .toMutableList(), // Pass the patient without treatments to avoid recursion
        address = address?.asDomain()
    )

    // Helper method to create a Patient domain object without treatments (to avoid infinite recursion)
    private fun asDomainWithoutTreatments() = Patient(
        id = id,
        name = name,
        lastName = lastName,
        birthDate = birthDate,
        specialHealthConditions = specialHealthConditions,
        gender = gender,
        allergicIngredients = allergicIngredients,
        treatments = mutableListOf(), // No treatments here
        address = address?.asDomain()
    )

    fun addTreatment(treatment: TreatmentEntity) {
        treatments.add(treatment)
        treatment.patient = this
    }

    fun updateFromDomain(patient: Patient) {  // refactor - move to Extensions?
        this.name = patient.name
        this.lastName = patient.lastName
        this.birthDate = patient.birthDate
        this.gender = patient.gender
        this.specialHealthConditions = patient.specialHealthConditions.toMutableList()
        this.allergicIngredients = patient.allergicIngredients.toMutableList()

        this.treatments.clear()
        this.treatments.addAll(patient.treatments.map { of(it, this) })

        this.address = patient.address?.let { of(it) }

        // TODO HERE Update treatments by replacing them completely


        // Update or replace address
    }
}
