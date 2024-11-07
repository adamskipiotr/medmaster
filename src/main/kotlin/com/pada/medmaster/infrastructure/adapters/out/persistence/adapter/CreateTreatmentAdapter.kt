package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter

import com.pada.medmaster.application.ports.out.CreateTreatmentPort
import com.pada.medmaster.domain.model.treatment.Treatment
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.TreatmentEntity
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.TreatmentRepository
import org.springframework.stereotype.Component

@Component
class CreateTreatmentAdapter(val treatmentRepository: TreatmentRepository) : CreateTreatmentPort {

    override fun createTreatment(treatment: Treatment) {
        val treatmentEntity = of(treatment)
        treatmentEntity.medicalProcedures.forEach { mp -> mp.treatment = treatmentEntity }
        treatmentEntity.intakes.forEach { intakeEntity -> intakeEntity.treatment = treatmentEntity }
        treatmentEntity.intakes.forEach { intakeEntity ->
            intakeEntity.intakeDates.forEach { intakeDateEntity ->
                intakeDateEntity.intake = intakeEntity
            }
        }
        treatmentEntity.intakes.forEach { intakeEntity ->
            intakeEntity.medicament!!.ingredients.forEach { ingredientEntity ->
                ingredientEntity.medicament = intakeEntity.medicament!!
            }
        }
        treatmentRepository.save(treatmentEntity)
    }
}