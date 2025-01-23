package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.treatment

import com.pada.medmaster.application.ports.out.treatment.UpdateTreatmentPort
import com.pada.medmaster.domain.model.patient.Treatment
import org.springframework.stereotype.Component
import com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.of

@Component
class UpdateTreatmentAdapter() : UpdateTreatmentPort {

    override fun update(treatment: Treatment){
        val treatmentEntity = of(treatment)
        //treatmentRepository.save(treatmentEntity)
    }

}