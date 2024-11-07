package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import com.pada.medmaster.domain.model.treatment.Intake
import jakarta.persistence.*

@Entity
@Table(name = "intake")
class IntakeEntity {
    @Id
    @SequenceGenerator(name = "intake_id_sequence", sequenceName = "intake_id_seq", allocationSize = 1)
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "medicament_id")
    var medicament: MedicamentEntity? = null

    @OneToMany(mappedBy = "intake", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    var intakeDates: MutableList<IntakeDateEntity>  = mutableListOf()

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatment_id")
    var treatment: TreatmentEntity? = null

    fun asDomain(): Intake {
        return Intake(
            id,
            medicament?.asDomain(),
            form,
            dosage,
            intakeFrequency,
            intakeDates.map { it.asDomain() }.toMutableList(),
            intakeLimit,
            null
        )
    }
}
