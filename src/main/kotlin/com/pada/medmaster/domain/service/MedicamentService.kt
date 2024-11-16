package com.pada.medmaster.domain.service

import com.pada.medmaster.application.dto.request.medicament.MedicamentRequestDTO
import com.pada.medmaster.application.ports.`in`.CreateMedicamentUseCase
import com.pada.medmaster.application.ports.out.CreateMedicamentPort
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

// Future refactoring target: Separate Services to handle defined Use Case
// see: https://raatiniemi.se/thoughts/use-case-driven-development/
@Service
class MedicamentService(
    val createMedicamentPort: CreateMedicamentPort
) : CreateMedicamentUseCase {

    @Transactional
    override fun create(medicamentRequestDTO: MedicamentRequestDTO) {
        val medicament = medicamentRequestDTO.toDomain()
        createMedicamentPort.createMedicament(medicament)
    }
}