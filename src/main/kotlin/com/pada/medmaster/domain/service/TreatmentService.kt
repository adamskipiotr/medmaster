package com.pada.medmaster.domain.service

import com.pada.medmaster.application.dto.request.treatment.TreatmentRequestDTO
import com.pada.medmaster.application.ports.`in`.CreateTreatmentUseCase
import com.pada.medmaster.application.ports.`in`.GetAllTreatmentsUseCase
import com.pada.medmaster.application.ports.`in`.GetTreatmentUseCase
import com.pada.medmaster.application.ports.out.CreateTreatmentPort
import com.pada.medmaster.application.ports.out.GetAllTreatmentsPort
import com.pada.medmaster.application.ports.out.GetTreatmentPort
import com.pada.medmaster.domain.model.treatment.Treatment
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

// Future refactoring target: Separate Services to handle defined Use Case
// see: https://raatiniemi.se/thoughts/use-case-driven-development/
@Service
class TreatmentService(
    val getTreatmentPort: GetTreatmentPort,
    val getAllTreatmentsPort: GetAllTreatmentsPort,
    val createTreatmentPort: CreateTreatmentPort
) : GetTreatmentUseCase, GetAllTreatmentsUseCase, CreateTreatmentUseCase {


    override fun getTreatment(code: String): Treatment {
        return getTreatmentPort.getTreatment("Placeholder")
    }

    @Transactional
    override fun getAllTreatments(): List<Treatment> {
        return getAllTreatmentsPort.getAllTreatments()
    }

    @Transactional
    override fun createTreatment(treatmentRequestDTO: TreatmentRequestDTO) {
        val treatment = treatmentRequestDTO.toDomain()
        createTreatmentPort.createTreatment(treatment)
    }
}