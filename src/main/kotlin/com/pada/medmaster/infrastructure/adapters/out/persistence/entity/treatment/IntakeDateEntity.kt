package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import com.pada.medmaster.domain.model.treatment.Intake
import com.pada.medmaster.domain.model.treatment.IntakeDate
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "intake_date")
class IntakeDateEntity(
    @Id
    @SequenceGenerator(name = "intake_date_id_sequence", sequenceName = "intake_date_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "intake_date_id_sequence")
    var id: Long = 0,
    val date: LocalDateTime,
    @ManyToOne
    val intake: IntakeEntity

    ) { // learn: internal in Kotlin vs package-in Java


    fun asDomain(): IntakeDate { // why  fun asDomain(): MedicalProcedure { doesnt work here
        return IntakeDate(
            // to learn: what is Local Extension
            id, date, intake.asDomain(),
        )
    }

    companion object {
        fun of(intakeDate: IntakeDate): IntakeDateEntity {
            return IntakeDateEntity(
                intakeDate.id ?: 0,
                intakeDate.date,
                IntakeEntity.of(intakeDate.intake)
            )
        }
    }
}