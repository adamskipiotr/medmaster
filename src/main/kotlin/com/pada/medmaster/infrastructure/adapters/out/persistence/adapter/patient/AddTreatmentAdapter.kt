package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.patient

import com.pada.medmaster.application.ports.out.patient.AddTreatmentPort
import com.pada.medmaster.domain.model.patient.Treatment
import com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.of
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.PatientRepository
import org.springframework.stereotype.Component

@Component
class AddTreatmentAdapter(val patientRepository: PatientRepository) : AddTreatmentPort {

    override fun execute(id: Long, treatment: Treatment) {
        val patientEntity = patientRepository.findById(id)
        val treatmentEntity = of(treatment)
        treatmentEntity.medicalProcedures.forEach { mp -> mp.treatment = treatmentEntity }
        treatmentEntity.intakes.forEach { intakeEntity -> intakeEntity.treatment = treatmentEntity }
        treatmentEntity.intakes.forEach { intakeEntity ->
            intakeEntity.intakeDates.forEach { intakeDateEntity ->
                intakeDateEntity.intake = intakeEntity
            }
        }
        patientEntity.addTreatment(treatmentEntity)
        patientRepository.save(patientEntity)
    }
}