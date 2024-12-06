package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.treatment

import com.pada.medmaster.application.ports.out.CreateTreatmentPort
import com.pada.medmaster.domain.model.treatment.Treatment
import com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.of
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.TreatmentRepository
import org.springframework.stereotype.Component

@Component
class CreateTreatmentAdapter(val treatmentRepository: TreatmentRepository) : CreateTreatmentPort {

    override fun createTreatment(treatment: Treatment): Treatment {
        val treatmentEntity = of(treatment)
        treatmentEntity.medicalProcedures.forEach { mp -> mp.treatment = treatmentEntity }
        treatmentEntity.intakes.forEach { intakeEntity -> intakeEntity.treatment = treatmentEntity }
        treatmentEntity.intakes.forEach { intakeEntity ->
            intakeEntity.intakeDates.forEach { intakeDateEntity ->
                intakeDateEntity.intake = intakeEntity
            }
        }
       return treatmentRepository.save(treatmentEntity).asDomain()
    }
}