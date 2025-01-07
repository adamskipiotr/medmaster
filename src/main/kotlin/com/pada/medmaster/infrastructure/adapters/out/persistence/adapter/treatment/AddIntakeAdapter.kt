package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.treatment

import com.pada.medmaster.application.ports.out.AddIntakePort
import com.pada.medmaster.application.ports.out.CreateTreatmentPort
import com.pada.medmaster.domain.model.treatment.Treatment
import com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.of
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.TreatmentRepository
import org.springframework.stereotype.Component

@Component
class AddIntakeAdapter(val treatmentRepository: TreatmentRepository) : AddIntakePort {

    override fun addIntake(treatment: Treatment) {
        val treatmentEntity = of(treatment)
        treatmentEntity.medicalProcedures.forEach { mp -> mp.treatment = treatmentEntity }
        treatmentEntity.intakes.forEach { intakeEntity -> intakeEntity.treatment = treatmentEntity }
        treatmentEntity.intakes.forEach { intakeEntity ->
            intakeEntity.intakeDates.forEach { intakeDateEntity ->
                intakeDateEntity.intake = intakeEntity
            }
        }
    }
}