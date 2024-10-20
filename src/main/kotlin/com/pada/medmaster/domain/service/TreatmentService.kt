package com.pada.medmaster.domain.service

import com.pada.medmaster.application.ports.`in`.GetTreatmentUseCase
import com.pada.medmaster.application.ports.out.GetTreatmentPort
import com.pada.medmaster.domain.model.treatment.Treatment
import org.springframework.stereotype.Service

@Service
class TreatmentService(val getTreatmentPort: GetTreatmentPort) : GetTreatmentUseCase {
    override fun getTreatment(code: String): Treatment {
        return getTreatmentPort.getTreatment("Placeholder")
    }

}