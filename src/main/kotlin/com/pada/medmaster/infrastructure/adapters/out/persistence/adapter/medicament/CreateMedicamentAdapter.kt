package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.medicament

import com.pada.medmaster.application.ports.out.medicament.CreateMedicamentPort
import com.pada.medmaster.domain.model.medicament.Medicament
import com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.of
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.MedicamentRepository
import org.springframework.stereotype.Component

@Component
class CreateMedicamentAdapter(val medicamentRepository: MedicamentRepository) : CreateMedicamentPort {

    override fun createMedicament(medicament: Medicament) {
        val medicamentEntity = of(medicament)
        medicamentRepository.save(medicamentEntity)
    }
}