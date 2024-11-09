package com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.medicament

import com.pada.medmaster.application.ports.out.CreateMedicamentPort
import com.pada.medmaster.application.ports.out.CreateTreatmentPort
import com.pada.medmaster.domain.model.treatment.Medicament
import com.pada.medmaster.domain.model.treatment.Treatment
import com.pada.medmaster.infrastructure.adapters.out.persistence.adapter.of
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.MedicamentRepository
import com.pada.medmaster.infrastructure.adapters.out.persistence.repository.TreatmentRepository
import org.springframework.stereotype.Component

@Component
class CreateMedicamentAdapter(val medicamentRepository: MedicamentRepository) : CreateMedicamentPort {

    override fun createMedicament(medicament: Medicament) {
        val medicamentEntity = of(medicament)
        medicamentRepository.save(medicamentEntity)
    }
}