package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import com.pada.medmaster.domain.model.treatment.Intake
import jakarta.persistence.*
import lombok.AllArgsConstructor

@Entity
@AllArgsConstructor
class IntakeEntity(
    @Id
    @SequenceGenerator(name = "intake_id_sequence", sequenceName = "intake_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "intake_id_sequence")
    var id: Long = 0,

    @Enumerated(EnumType.STRING)  // Use @Enumerated to map the enum
    @Column(name = "form")
    val form: IntakeForm,  // No need for @ManyToOne, this is an enum

    @Column(nullable = false)
    val dosage: Int,

    @Enumerated(EnumType.STRING)  // Use @Enumerated to map the enum
    @Column(name = "intakeFrequency")
    val intakeFrequency: IntakeFrequency,

    @Column(nullable = false)
    val intakeLimit: Int,
    ) {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicament_id")
    lateinit var medicament: MedicamentEntity

    @OneToMany(mappedBy = "intake", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    val intakeDates: List<IntakeDateEntity> = emptyList()  // Initialize the list

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatment_id")
    lateinit var treatment: TreatmentEntity


    fun asDomain(): Intake {
        return Intake(
            id = id,
            form = form,
            dosage = dosage,
            intakeFrequency = intakeFrequency,
            intakeLimit = intakeLimit,
            medicament = medicament.asDomain(),
            intakeDates = intakeDates.map { it.asDomain() },
            treatment = treatment.asDomain()
        )
    }
}
