package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
 class MedicalProcedure(
    @Id
    @SequenceGenerator(name = "medical_procedure_id_sequence", sequenceName = "medical_procedure_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medical_procedure_id_sequence")
    var id: Long = 0,
    private val name: String,
    private val description: String,
    private val procedureDate: LocalDateTime,
    private val minimalRecoveryDate: LocalDateTime,
    @ManyToOne
    private val  treatment: Treatment
) {





}