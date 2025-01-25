package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient

import com.pada.medmaster.domain.model.patient.Patient
import com.pada.medmaster.domain.model.patient.Treatment
import jakarta.persistence.*
import java.time.LocalDateTime

// Aggregate Root: Treatment
// To do - why entity should not be data class when using JPA
// to do - also apply https://dzone.com/articles/bets-practices-of-using-jpa-with-kotlin
// also https://medium.com/@goncharov.valentin/a-practical-point-of-view-on-kotlin-data-classes-and-jpa-hibernate-c69370b975e1
@Entity
@Table(schema = "patient_schema", name = "treatment")
class TreatmentEntity {

    lateinit var code: String
    lateinit var disease: String
    lateinit var description: String
    lateinit var beginDate: LocalDateTime
    lateinit var endDate: LocalDateTime

    @Id
    @SequenceGenerator(
        schema = "patient_schema",
        name = "treatment_id_sequence",
        sequenceName = "treatment_id_seq",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "treatment_id_sequence")
    var id: Long = 0

    @OneToMany(
        mappedBy = "treatment", cascade = [CascadeType.ALL],
        fetch = FetchType.EAGER, orphanRemoval = true
    )
    var medicalProcedures: MutableList<MedicalProcedureEntity> = mutableListOf()

    @ManyToOne
    @JoinColumn(name = "patient_id")
    var patient: PatientEntity? = null

    @OneToMany(
        mappedBy = "treatment", cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY, orphanRemoval = true
    )
    var intakes: MutableList<IntakeEntity> = mutableListOf()

    fun asDomain(mappedPatient: Patient? = null) = Treatment(
        id = id,
        disease = disease,
        description = description,
        code = code,
        medicalProcedures = medicalProcedures.map { it.asDomain() }.toMutableList(),
        patient = mappedPatient,  // Pass the patient here
        intakes = intakes.map { it.asDomain(this.asDomainWithoutIntakes()) }.toMutableList(),
        beginDate = beginDate,
        endDate = endDate
    )


    fun asDomainWithoutIntakes(mappedPatient: Patient? = null) = Treatment(
        id = id,
        disease = disease,
        description = description,
        code = code,
        medicalProcedures = medicalProcedures.map { it.asDomain() }.toMutableList(),
        patient = mappedPatient,  // Pass the patient here
        intakes = mutableListOf(), // No treatments here
        beginDate = beginDate,
        endDate = endDate
    )
}
