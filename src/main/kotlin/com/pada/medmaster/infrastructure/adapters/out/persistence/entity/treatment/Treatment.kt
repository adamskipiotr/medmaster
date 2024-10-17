package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import java.time.LocalDateTime
import jakarta.persistence.*
@Entity
@Table(name = "treatment")
class Treatment(
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
    val disease: String,
    val description: String,
    val medicalProcedures: List<MedicalProcedure>,
    val intakes: List<Intake>,
    val beginDate: LocalDateTime,
    val endDate: LocalDateTime
) {


}