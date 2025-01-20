package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import com.pada.medmaster.domain.model.treatment.MedicalProcedure
import jakarta.persistence.*
import java.time.LocalDateTime


// Important see: https://stackoverflow.com/questions/45642181/kotlin-jpa-encapsulate-onetomany
@Entity
@Table(schema = "treatment_schema", name = "medical_procedure")
class MedicalProcedureEntity {
    @Id
    @SequenceGenerator(
        schema = "treatment_schema",
        name = "medical_procedure_id_sequence",
        sequenceName = "medical_procedure_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medical_procedure_id_sequence")
    var id: Long = 0
    lateinit var name: String
    lateinit var description: String
    lateinit var procedureDate: LocalDateTime
    lateinit var minimalRecoveryDate: LocalDateTime

    @ManyToOne
    var treatment: TreatmentEntity? = null

    fun asDomain(): MedicalProcedure { // why  fun asDomain(): MedicalProcedure { doesnt work here
        return MedicalProcedure(       // to learn: what is Local Extension
            id,                   // TODO: move it to extension file
            name,
            description,
            procedureDate,
            minimalRecoveryDate,
            null
        )
    }
}