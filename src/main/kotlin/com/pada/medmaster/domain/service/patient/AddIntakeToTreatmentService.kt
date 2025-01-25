package com.pada.medmaster.domain.service.patient

import com.pada.medmaster.application.dto.request.treatment.CreateIntakeRequest
import com.pada.medmaster.application.ports.`in`.patient.AddIntakeUseCase
import com.pada.medmaster.application.ports.out.patient.GetPatientPort
import com.pada.medmaster.application.ports.out.patient.UpdatePatientPort
import com.pada.medmaster.domain.model.patient.Intake
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

// Future refactoring target: Separate Services to handle defined Use Case
// see: https://raatiniemi.se/thoughts/use-case-driven-development/
@Service
class AddIntakeToTreatmentService(
    val getPatientPort: GetPatientPort,
    val updatePatientPort: UpdatePatientPort,
) : AddIntakeUseCase {


    @Transactional
    override fun addIntake(patientId: Long, treatmentId: Long,createIntakeRequest: CreateIntakeRequest) {
        val patient = getPatientPort.get(patientId)
        val intake = Intake(
            null, createIntakeRequest.medicamentId, createIntakeRequest.form, createIntakeRequest.dosage,
            createIntakeRequest.intakeFrequency, mutableListOf(), createIntakeRequest.intakeLimit, null
        )

        patient.addIntakeToTreatment(treatmentId, intake)

        updatePatientPort.update(patient)
    }
}