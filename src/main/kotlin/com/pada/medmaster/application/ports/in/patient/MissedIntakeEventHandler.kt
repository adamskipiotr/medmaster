package com.pada.medmaster.application.ports.`in`.patient

import com.pada.medmaster.application.dto.request.patient.ReportIntakeRequest
import com.pada.medmaster.domain.events.MissedIntakeEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class MissedIntakeEventHandler {

    @EventListener
    fun handleMissedIntakeEvent(event: MissedIntakeEvent) {
        println("Handling MissedIntakeEvent for patient: ${event.patientId} \n Missed intake: ${event.intakeId} in treatment: ${event.treatmentId}")

        // Perform actions like notifying a patient, logging, or triggering further business logic.
    }
}