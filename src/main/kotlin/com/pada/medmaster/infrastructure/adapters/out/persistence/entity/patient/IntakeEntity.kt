package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient

import com.pada.medmaster.domain.model.patient.Intake
import com.pada.medmaster.domain.model.patient.Treatment
import jakarta.persistence.*

@Entity
@Table(schema = "patient_schema", name = "intake")
class IntakeEntity {
    @Id
    @SequenceGenerator(
        schema = "patient_schema",
        name = "intake_id_sequence",
        sequenceName = "intake_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "intake_id_sequence")
    var id: Long = 0

    @Enumerated(EnumType.STRING)  // Use @Enumerated to map the enum
    @Column(name = "form")
    lateinit var form: IntakeForm  // No need for @ManyToOne, this is an enum

    @Column(nullable = false)
    var dosage: Int = 0

    @Enumerated(EnumType.STRING)  // Use @Enumerated to map the enum
    @Column(name = "intakeFrequency")
    lateinit var intakeFrequency: IntakeFrequency

    @Column(nullable = false)
    var intakeLimit: Int = 0

    var medicamentId: Long? = null // Object inside Aggregate can have reference to other Aggregate under 2 conditions:
    // 1. Only to Aggregate Root
    // 2. Only by its ID
    // see more: "Domain-Driven Design" by Evans, "Implementing Domain-Driven Design" by Vernon

    @OneToMany(mappedBy = "intake", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    var intakeDates: MutableList<IntakeDateEntity> = mutableListOf()

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatment_id")
    var treatment: TreatmentEntity? = null

    fun asDomain(mappedTreatment: Treatment): Intake {
        return Intake(
            id,
            medicamentId, // after change from relation to ID - expected behaviour is to fetch Medicament for Intake based on medicamentId
            com.pada.medmaster.domain.model.patient.IntakeForm.valueOf(form.name),
            dosage,
            com.pada.medmaster.domain.model.patient.IntakeFrequency.valueOf(intakeFrequency.name),
            intakeDates.map { it.asDomain() }.toMutableList(),
            intakeLimit,
            mappedTreatment
        )
    }
}
