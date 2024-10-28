package com.pada.medmaster.domain.service

import com.pada.medmaster.application.dto.request.TreatmentRequestDTO
import com.pada.medmaster.application.ports.`in`.CreateTreatmentUseCase
import com.pada.medmaster.application.ports.`in`.GetTreatmentUseCase
import com.pada.medmaster.application.ports.out.CreateTreatmentPort
import com.pada.medmaster.application.ports.out.GetTreatmentPort
import com.pada.medmaster.domain.model.treatment.*
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class TreatmentService(
    val getTreatmentPort: GetTreatmentPort,
    val createTreatmentPort: CreateTreatmentPort
) : GetTreatmentUseCase, CreateTreatmentUseCase {
    override fun getTreatment(code: String): Treatment {
        return getTreatmentPort.getTreatment("Placeholder")
    }

    @Transactional
    override fun createTreatment(treatmentRequestDTO: TreatmentRequestDTO) {
        val treatment = treatmentRequestDTO.toDomain()
        createTreatmentPort.createTreatment(treatment)
    }
}