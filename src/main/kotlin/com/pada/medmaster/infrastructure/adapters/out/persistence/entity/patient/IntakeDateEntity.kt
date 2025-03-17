package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient

import com.pada.medmaster.domain.model.patient.IntakeDate
import jakarta.persistence.*
import java.time.Clock
import java.time.LocalDateTime

@Entity
@Table(schema = "patient_schema", name = "intake_date")
class IntakeDateEntity{
    @Id
    @SequenceGenerator(
        schema = "patient_schema",
        name = "intake_date_id_sequence",
        sequenceName = "intake_date_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "intake_date_id_sequence")
    var id: Long = 0
    lateinit var date: LocalDateTime
    var expectedDateMinGap: LocalDateTime? = null
    var expectedDateMaxGap: LocalDateTime? = null
    var intakeInTimeGap: Boolean = false
    var overdose: Boolean = false
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var intake: IntakeEntity? = null

  // learn: internal in Kotlin vs package-in Java

    fun asDomain(): IntakeDate {
        return IntakeDate(
            // to learn: what is Local Extension
            id, date, expectedDateMinGap, expectedDateMaxGap, intakeInTimeGap, overdose, null
        )
    }

}