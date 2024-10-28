package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import com.pada.medmaster.domain.model.treatment.Treatment
import java.time.LocalDateTime
import jakarta.persistence.*

@Entity
@Table(name = "treatment")
data class TreatmentEntity(
    @Id
    @SequenceGenerator(name = "treatment_id_sequence", sequenceName = "treatment_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "treatment_id_sequence")
    var id: Long = 0,
    val code: String,
    val disease: String,
    val description: String,
    val beginDate: LocalDateTime,
    val endDate: LocalDateTime,
    @OneToMany(
        mappedBy = "treatment", cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY, orphanRemoval = true
    )
    val medicalProcedures: List<MedicalProcedureEntity>,

    @OneToMany(
        mappedBy = "treatment", cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY, orphanRemoval = true
    )
    val intakes: List<IntakeEntity> = listOf()
) {

    fun asDomain() =
        Treatment(
            id, disease, description, code,
            this.medicalProcedures.map { it.asDomain() },
            intakes.map { it.asDomain() }, beginDate, endDate
        )

    companion object {
        fun of(treatment: Treatment) =
            TreatmentEntity(
                treatment.id ?: 0,
                treatment.disease,
                treatment.description,
                treatment.code,
                treatment.beginDate,
                treatment.endDate,
                treatment.medicalProcedures.map { MedicalProcedureEntity.of(it) },
                treatment.intakes.map { IntakeEntity.of(it) }
            )
    }
}
