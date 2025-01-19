package com.pada.medmaster.domain.service.treatment

import com.pada.medmaster.application.ports.`in`.GetAllTreatmentsUseCase
import com.pada.medmaster.application.ports.out.treatment.GetAllTreatmentsPort
import com.pada.medmaster.domain.model.treatment.Treatment
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

// Future refactoring target: Separate Services to handle defined Use Case
// see: https://raatiniemi.se/thoughts/use-case-driven-development/
@Service
class GetAllTreatmentsService(
    val getAllTreatmentsPort: GetAllTreatmentsPort,
) : GetAllTreatmentsUseCase {

    @Transactional
    override fun execute(): List<Treatment> {
        return getAllTreatmentsPort.getAllTreatments()
    }
}