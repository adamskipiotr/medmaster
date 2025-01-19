package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.treatment

import com.pada.medmaster.application.ports.out.treatment.UpdateTreatmentPort
import com.pada.medmaster.domain.model.treatment.Treatment
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.TreatmentRepository
import org.springframework.stereotype.Component
import com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.of

@Component
class UpdateTreatmentAdapter(val treatmentRepository: TreatmentRepository) : UpdateTreatmentPort {

    override fun update(treatment: Treatment){
        val treatmentEntity = of(treatment)
        treatmentRepository.save(treatmentEntity)
    }

}