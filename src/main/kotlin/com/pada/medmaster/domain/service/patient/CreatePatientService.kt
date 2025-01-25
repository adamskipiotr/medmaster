package com.pada.medmaster.domain.service.patient

import com.pada.medmaster.application.dto.request.patient.CreatePatientRequest
import com.pada.medmaster.application.ports.`in`.patient.CreatePatientUseCase
import com.pada.medmaster.application.ports.out.patient.AddTreatmentToPatientPort
import com.pada.medmaster.application.ports.out.patient.CreatePatientPort
import com.pada.medmaster.domain.events.TreatmentAddedEvent
import com.pada.medmaster.domain.service.toDomain
import jakarta.transaction.Transactional
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

// Future refactoring target: Separate Services to handle defined Use Case
// see: https://raatiniemi.se/thoughts/use-case-driven-development/
@Service
class CreatePatientService(
    val createPatientPort: CreatePatientPort,
    val addTreatmentToPatientPort: AddTreatmentToPatientPort
) : CreatePatientUseCase {

    @Transactional
    override fun create(createPatientRequest: CreatePatientRequest) {
        val patient = createPatientRequest.toDomain()
        createPatientPort.create(patient)
    }

    @EventListener
    fun handleTreatmentAddedEvent(event: TreatmentAddedEvent) {
        addTreatmentToPatientPort.addTreatment(event.patientId, event.treatmmentId)
    }
}