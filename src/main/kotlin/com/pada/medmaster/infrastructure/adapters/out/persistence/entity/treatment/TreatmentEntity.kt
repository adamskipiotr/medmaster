package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import com.pada.medmaster.domain.model.treatment.Treatment
import java.time.LocalDateTime
import jakarta.persistence.*
import lombok.Builder

@Builder
@Entity
@Table(name = "treatment")
// To learn : Why data classes are not recommended as JPA Entities
data class TreatmentEntity(
    @Id
    @SequenceGenerator(name = "treatment_id_sequence", sequenceName = "treatment_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "treatment_id_sequence")
    var id: Long = 0,
    val disease: String,
    val description: String,
    val beginDate: LocalDateTime,
    val endDate: LocalDateTime
) { // values in () are defined in primary constructor, they will be automatically used in equals(), hashCode(), toString() methods
    // (in data class, check what in normal class). Since below are relationships, it is better not to put them inside primary constructor (why)
    @OneToMany(
        mappedBy = "treatment", cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY, orphanRemoval = true
    )
    @Builder.Default
    val medicalProcedures: List<MedicalProcedureEntity> = ArrayList()

    @OneToMany(
        mappedBy = "treatment", cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY, orphanRemoval = true
    )
    @Builder.Default
    val intakes: List<IntakeEntity> = ArrayList()

    fun asDomain(): Treatment {  // to learn: Extension function
        return Treatment(
            id = id,
            disease = disease,
            description = description,
            beginDate = beginDate,
            endDate = endDate,
            medicalProcedures = this.medicalProcedures.map { it.asDomain() },
            intakes = intakes
        )
    }

}



