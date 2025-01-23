package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.treatment

import com.pada.medmaster.application.ports.out.treatment.AddIntakePort
import com.pada.medmaster.domain.model.patient.Treatment
import com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.of
import org.springframework.stereotype.Component

@Component
class AddIntakeAdapter() : AddIntakePort {

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