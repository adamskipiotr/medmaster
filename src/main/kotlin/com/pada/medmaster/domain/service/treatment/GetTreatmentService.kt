package com.pada.medmaster.domain.service.treatment

import com.pada.medmaster.application.ports.`in`.patient.GetTreatmentUseCase
import com.pada.medmaster.application.ports.out.patient.GetActiveTreatmentsByCodePort
import com.pada.medmaster.domain.model.patient.Treatment
import org.springframework.stereotype.Service

// Future refactoring target: Separate Services to handle defined Use Case
// see: https://raatiniemi.se/thoughts/use-case-driven-development/
@Service
class GetTreatmentService(
    val getActiveTreatmentsByCodePort: GetActiveTreatmentsByCodePort,
) : GetTreatmentUseCase {

    override fun execute(code: String): Treatment {
        return getActiveTreatmentsByCodePort.get("Code").first()
    }
}