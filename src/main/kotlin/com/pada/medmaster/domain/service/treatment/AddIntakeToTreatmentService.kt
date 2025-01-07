package com.pada.medmaster.domain.service.treatment

import com.pada.medmaster.application.dto.request.treatment.AddIntakeDTO
import com.pada.medmaster.application.ports.`in`.AddIntakeUseCase
import com.pada.medmaster.application.ports.`in`.GetAllTreatmentsUseCase
import com.pada.medmaster.application.ports.out.*
import com.pada.medmaster.domain.model.treatment.Treatment
import jakarta.transaction.Transactional
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

// Future refactoring target: Separate Services to handle defined Use Case
// see: https://raatiniemi.se/thoughts/use-case-driven-development/
@Service
class AddIntakeToTreatmentService(
    val getTreatmentPort: GetTreatmentPort,
    val addIntakePort: AddIntakePort,
) : AddIntakeUseCase {


    override fun addIntake(id: Long, addIntakeDTO: AddIntakeDTO) {
        val treatment = getTreatmentPort.get(id)

        val takenMedicaments = treatment.intakes.map { k -> k.medicamentId }

    }
}