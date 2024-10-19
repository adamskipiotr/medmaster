package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import jakarta.persistence.*
import lombok.Builder
import java.time.LocalDateTime

@Entity
class IntakeDate(
    @Id
    @SequenceGenerator(name = "intake_date_id_sequence", sequenceName = "intake_date_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "intake_date_id_sequence")
    var id: Long = 0,
    private val date: LocalDateTime,
    @ManyToOne
    private val intake: Intake
){ // learn: internal in Kotlin vs package-private in Java
}