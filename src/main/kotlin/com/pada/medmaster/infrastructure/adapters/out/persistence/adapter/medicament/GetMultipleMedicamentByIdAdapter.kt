package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.medicament

import com.pada.medmaster.application.ports.out.medicament.GetMultipleMedicamentByIdPort
import com.pada.medmaster.domain.model.medicament.Medicament
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.MedicamentRepository
import org.springframework.stereotype.Component

@Component
class GetMultipleMedicamentByIdAdapter(val medicamentRepository: MedicamentRepository) : GetMultipleMedicamentByIdPort {

    override fun get(ids: List<Long?>): List<Medicament> {
        return medicamentRepository.findAllById(ids).map { it.asDomain() }
    }
}