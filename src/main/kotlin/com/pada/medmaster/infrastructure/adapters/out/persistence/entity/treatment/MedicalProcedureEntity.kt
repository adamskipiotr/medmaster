package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import com.pada.medmaster.domain.model.treatment.MedicalProcedure
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class MedicalProcedureEntity(
    @Id
    @SequenceGenerator(name = "medical_procedure_id_sequence", sequenceName = "medical_procedure_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medical_procedure_id_sequence")
    var id: Long = 0,
    val name: String,
    val description: String,
    val procedureDate: LocalDateTime,
    val minimalRecoveryDate: LocalDateTime,

) {

    @ManyToOne
    lateinit var  treatment: TreatmentEntity
    fun asDomain(): MedicalProcedure { // why  fun asDomain(): MedicalProcedure { doesnt work here
        return MedicalProcedure(       // to learn: what is Local Extension
            id = id,                   // TODO: move it to extension file
            name = name,
            description = description,
            procedureDate = procedureDate,
            minimalRecoveryDate = minimalRecoveryDate,
            treatment = treatment
        )
    }
 }