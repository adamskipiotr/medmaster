package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import com.pada.medmaster.domain.model.treatment.IntakeDate
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class IntakeDateEntity(
    @Id
    @SequenceGenerator(name = "intake_date_id_sequence", sequenceName = "intake_date_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "intake_date_id_sequence")
    var id: Long = 0,
    val date: LocalDateTime,

){ // learn: internal in Kotlin vs package-in Java
    @ManyToOne
    lateinit var intake: IntakeEntity

    fun asDomain(): IntakeDate { // why  fun asDomain(): MedicalProcedure { doesnt work here
        return IntakeDate(       // to learn: what is Local Extension
            id = id,
            date = date,
            intake = intake.asDomain(),
        )
    }
}