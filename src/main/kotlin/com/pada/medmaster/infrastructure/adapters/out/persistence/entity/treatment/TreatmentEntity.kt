package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import com.pada.medmaster.domain.model.treatment.Treatment
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "treatment")
class TreatmentEntity {  // Aggregate Root

    lateinit var code: String
    lateinit var disease: String
    lateinit var description: String
    lateinit var beginDate: LocalDateTime
    lateinit var endDate: LocalDateTime

    @Id
    @SequenceGenerator(name = "treatment_id_sequence", sequenceName = "treatment_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "treatment_id_sequence")
    var id: Long = 0

    @OneToMany(
        mappedBy = "treatment", cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER, orphanRemoval = true
    )
    var medicalProcedures: MutableList<MedicalProcedureEntity> = mutableListOf()

    @OneToMany(
        mappedBy = "treatment", cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY, orphanRemoval = true
    )
    var intakes: MutableList<IntakeEntity> = mutableListOf()

    fun asDomain() = Treatment(
        id,
        disease,
        description,
        code,
        medicalProcedures.map { it.asDomain() }.toMutableList(),
        intakes.map { it.asDomain() }.toMutableList(),
        beginDate,
        endDate
    )
}
