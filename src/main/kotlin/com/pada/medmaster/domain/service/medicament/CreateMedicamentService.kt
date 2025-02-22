package com.pada.medmaster.domain.service.medicament

import com.pada.medmaster.application.dto.request.medicament.CreateMedicamentRequest
import com.pada.medmaster.application.ports.`in`.medicament.CreateMedicamentUseCase
import com.pada.medmaster.application.ports.out.medicament.CreateMedicamentPort
import com.pada.medmaster.domain.service.toDomain
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

// Future refactoring target: Separate Services to handle defined Use Case
// see: https://raatiniemi.se/thoughts/use-case-driven-development/
@Service
class CreateMedicamentService(
    val createMedicamentPort: CreateMedicamentPort
) : CreateMedicamentUseCase {

    @Transactional
    override fun create(createMedicamentRequest: CreateMedicamentRequest) {
        val medicament = createMedicamentRequest.toDomain()
        createMedicamentPort.create(medicament)
    }
}