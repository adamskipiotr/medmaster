package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import com.pada.medmaster.domain.model.treatment.Intake
import com.pada.medmaster.domain.model.treatment.Medicament
import jakarta.persistence.*
import lombok.AllArgsConstructor

@Entity
@AllArgsConstructor
@Table(name = "intake")
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

    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "medicament_id")
    var medicament: MedicamentEntity?,

    @OneToMany(mappedBy = "intake", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    val intakeDates: List<IntakeDateEntity>,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatment_id")
    var treatment: TreatmentEntity?,
) {

    fun asDomain(): Intake {
        return Intake(
            id,
            medicament?.asDomain(),
            form,
            dosage,
            intakeFrequency,
            intakeDates.map { it.asDomain() },
            intakeLimit,
            treatment!!.asDomain()
        )
    }

    companion object {
        fun of(intake: Intake): IntakeEntity {
            return IntakeEntity(
                intake.id ?: 0,
                intake.form,
                intake.dosage,
                intake.intakeFrequency!!,
                intake.intakeLimit,
                MedicamentEntity.of(intake.medicament!!),
                intake.intakeDates?.map { IntakeDateEntity.of(it) } ?: emptyList(),
                null
            )
        }
    }
}
