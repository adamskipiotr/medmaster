package com.pada.medmaster.application.service

import com.pada.medmaster.domain.service.patient.ScheduledIntakeValidationService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

// Future refactoring target: Separate Services to handle defined Use Case
// see: https://raatiniemi.se/thoughts/use-case-driven-development/
@Service
class IntakeValidationScheduler(
    val scheduledIntakeValidationService: ScheduledIntakeValidationService
)  {

    @Scheduled(cron = "0 0 1 * * *")
    fun runScheduledIntakeValidation() {
        scheduledIntakeValidationService.validateIntakes()
    }
}