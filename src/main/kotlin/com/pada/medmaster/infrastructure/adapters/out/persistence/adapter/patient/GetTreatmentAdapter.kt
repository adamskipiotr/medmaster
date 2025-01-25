package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.patient

import com.pada.medmaster.application.ports.out.patient.GetTreatmentPort
import com.pada.medmaster.domain.model.patient.Treatment
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class GetTreatmentAdapter() : GetTreatmentPort {
    override fun get(id: Long): Treatment {
        return Treatment(1L,"dis", "desc", "code", mutableListOf(),null, mutableListOf(), LocalDateTime.now(), LocalDateTime.now() );//treatmentRepository.findById(id).asDomain()
    }

}