package com.pada.medmaster.domain.service.patient

import com.pada.medmaster.application.dto.request.treatment.CreateMedicalProcedureRequest
import com.pada.medmaster.application.ports.`in`.patient.AddMedicalProcedureUseCase
import com.pada.medmaster.application.ports.out.patient.GetPatientPort
import com.pada.medmaster.application.ports.out.patient.UpdatePatientPort
import com.pada.medmaster.domain.service.toDomain
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

// Future refactoring target: Separate Services to handle defined Use Case
// see: https://raatiniemi.se/thoughts/use-case-driven-development/
@Service
class AddMedicalProcedureToTreatmentService(
    val getPatientPort: GetPatientPort,
    val updatePatientPort: UpdatePatientPort
) : AddMedicalProcedureUseCase {

    @Transactional
    override fun add(patientId: Long, treatmentId: Long, createMedicalProcedureRequest: CreateMedicalProcedureRequest) {
        val medicalProcedure = createMedicalProcedureRequest.toDomain()

        val patient = getPatientPort.get(patientId)

        patient.addMedicalProcedureToTreatment(treatmentId, medicalProcedure)

        updatePatientPort.update(patient)
    }
}