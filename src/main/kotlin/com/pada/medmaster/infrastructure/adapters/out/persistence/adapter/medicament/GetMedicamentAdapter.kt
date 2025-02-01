package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.medicament

import com.pada.medmaster.application.ports.out.medicament.CreateMedicamentPort
import com.pada.medmaster.application.ports.out.medicament.GetMedicamentPort
import com.pada.medmaster.domain.model.medicament.Medicament
import com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.of
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.MedicamentRepository
import org.springframework.stereotype.Component

@Component
class GetMedicamentAdapter(val medicamentRepository: MedicamentRepository) : GetMedicamentPort {

    override fun get(id: Long) : Medicament{
       return medicamentRepository.findById(id).orElseThrow { RuntimeException("No medicament with Id $id was found") }.asDomain()
    }
}