package com.pada.medmaster.domain.service.treatment

import com.pada.medmaster.application.dto.request.treatment.CreateTreatmentRequest
import com.pada.medmaster.application.ports.`in`.CreateTreatmentUseCase
import com.pada.medmaster.application.ports.out.treatment.CreateTreatmentPort
import com.pada.medmaster.domain.events.TreatmentAddedEvent
import com.pada.medmaster.domain.service.toDomain
import jakarta.transaction.Transactional
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

// Future refactoring target: Separate Services to handle defined Use Case
// see: https://raatiniemi.se/thoughts/use-case-driven-development/
@Service
class CreateTreatmentService(
    val createTreatmentPort: CreateTreatmentPort,
    val eventPublisher: ApplicationEventPublisher
) : CreateTreatmentUseCase {

    @Transactional
    override fun execute(createTreatmentRequest: CreateTreatmentRequest) {
        val treatment = createTreatmentRequest.toDomain()

        val savedTreatment = createTreatmentPort.createTreatment(treatment)
        eventPublisher.publishEvent(
            TreatmentAddedEvent(
                createTreatmentRequest.patientId,
                savedTreatment.id!!
            )
        ) // refactor - dependency to Spring here
    }
}