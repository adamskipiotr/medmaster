package com.pada.medmaster.domain.service.treatment

import com.pada.medmaster.application.dto.request.treatment.CreateTreatmentRequest
import com.pada.medmaster.application.ports.`in`.AddPatientTreatment
import com.pada.medmaster.application.ports.out.treatment.AddTreatmentPort
import com.pada.medmaster.domain.events.TreatmentAddedEvent
import com.pada.medmaster.domain.service.toDomain
import jakarta.transaction.Transactional
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

// Future refactoring target: Separate Services to handle defined Use Case
// see: https://raatiniemi.se/thoughts/use-case-driven-development/
@Service
class AddPatientTreatmentService(
    val addTreatmentPort: AddTreatmentPort,
    val eventPublisher: ApplicationEventPublisher
) : AddPatientTreatment {

    @Transactional
    override fun execute(id: Long, createTreatmentRequest: CreateTreatmentRequest) {
        val treatment = createTreatmentRequest.toDomain()

        addTreatmentPort.execute(id, treatment)
        eventPublisher.publishEvent(
            TreatmentAddedEvent(
                createTreatmentRequest.patientId,
                1000
            )
        ) // refactor - dependency to Spring here
    }
}