package com.pada.medmaster.domain.service.patient

import com.pada.medmaster.application.dto.request.patient.CreateTreatmentRequest
import com.pada.medmaster.application.ports.`in`.patient.AddPatientTreatmentUseCase
import com.pada.medmaster.application.ports.out.patient.GetPatientPort
import com.pada.medmaster.application.ports.out.patient.UpdatePatientPort
import com.pada.medmaster.domain.service.toDomain
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

// Future refactoring target: Separate Services to handle defined Use Case
// see: https://raatiniemi.se/thoughts/use-case-driven-development/
@Service
class AddPatientTreatmentService(
    val getPatientPort: GetPatientPort,
    val updatePatientPort: UpdatePatientPort,
) : AddPatientTreatmentUseCase {

    @Transactional
    override fun execute(id: Long, createTreatmentRequest: CreateTreatmentRequest) {
        val treatment = createTreatmentRequest.toDomain()
        val patient = getPatientPort.get(id)
        patient.addTreatment(treatment)

        updatePatientPort.update(patient)
    }
}