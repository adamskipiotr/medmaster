package com.pada.medmaster.domain.service

import com.pada.medmaster.application.dto.request.treatment.TreatmentRequestDTO
import com.pada.medmaster.application.ports.`in`.CreateTreatmentUseCase
import com.pada.medmaster.application.ports.`in`.GetAllTreatmentsUseCase
import com.pada.medmaster.application.ports.`in`.GetTreatmentUseCase
import com.pada.medmaster.application.ports.out.CreateTreatmentPort
import com.pada.medmaster.application.ports.out.GetAllTreatmentsPort
import com.pada.medmaster.application.ports.out.GetActiveTreatmentsByCodePort
import com.pada.medmaster.domain.model.treatment.Treatment
import com.pada.medmaster.domain.model.treatment.TreatmentCode
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

// Future refactoring target: Separate Services to handle defined Use Case
// see: https://raatiniemi.se/thoughts/use-case-driven-development/
@Service
class TreatmentService(
    val getActiveTreatmentsByCodePort: GetActiveTreatmentsByCodePort,
    val getAllTreatmentsPort: GetAllTreatmentsPort,
    val createTreatmentPort: CreateTreatmentPort,
) : GetTreatmentUseCase, GetAllTreatmentsUseCase, CreateTreatmentUseCase {


    override fun getTreatment(code: String): Treatment {
        return getActiveTreatmentsByCodePort.get(TreatmentCode("Code", 0)).first()
    }

    @Transactional
    override fun getAllTreatments(): List<Treatment> {
        return getAllTreatmentsPort.getAllTreatments()
    }

    @Transactional
    override fun createTreatment(treatmentRequestDTO: TreatmentRequestDTO) {
        val treatment = treatmentRequestDTO.toDomain()
        val activeTreatmentsWithCode = getActiveTreatmentsByCodePort.get(treatment.code)

        treatment.validateCreation(activeTreatmentsWithCode.size)

        createTreatmentPort.createTreatment(treatment)
    }
}