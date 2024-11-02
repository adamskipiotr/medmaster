package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter

import com.pada.medmaster.application.ports.out.CreateTreatmentPort
import com.pada.medmaster.domain.model.treatment.Treatment
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.TreatmentEntity
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.TreatmentRepository
import org.springframework.stereotype.Component

@Component
class CreateTreatmentAdapter(val treatmentRepository: TreatmentRepository) : CreateTreatmentPort {

    override fun createTreatment(treatment: Treatment) {
        val treatmentEntity = TreatmentEntity.of(treatment)
        treatmentEntity.medicalProcedures.forEach { mp -> mp.treatment = treatmentEntity }
        treatmentEntity.intakes.forEach { intakeEntity -> intakeEntity.treatment = treatmentEntity }
        treatmentRepository.save(treatmentEntity)
    }
}