package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import com.pada.medmaster.domain.model.treatment.MedicalProcedure
import jakarta.persistence.*
import java.time.LocalDateTime


// Important see: https://stackoverflow.com/questions/45642181/kotlin-jpa-encapsulate-onetomany
@Entity
@Table(name = "medical_procedure")
data class MedicalProcedureEntity(
    @Id
    @SequenceGenerator(
        name = "medical_procedure_id_sequence",
        sequenceName = "medical_procedure_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medical_procedure_id_sequence")
    var id: Long = 0,
    val name: String,
    val description: String,
    val procedureDate: LocalDateTime,
    val minimalRecoveryDate: LocalDateTime,
    @ManyToOne
    var treatment: TreatmentEntity? = null
) {
    fun asDomain(): MedicalProcedure { // why  fun asDomain(): MedicalProcedure { doesnt work here
        return MedicalProcedure(       // to learn: what is Local Extension
            id,                   // TODO: move it to extension file
            name,
            description,
            procedureDate,
            minimalRecoveryDate,
            treatment?.asDomain()
        )
    }

    companion object {
        fun of(medicalProcedure: MedicalProcedure) =
            MedicalProcedureEntity(
                medicalProcedure.id ?: 0,
                medicalProcedure.name,
                medicalProcedure.description,
                medicalProcedure.procedureDate,
                medicalProcedure.minimalRecoveryDate

            )
    }
}