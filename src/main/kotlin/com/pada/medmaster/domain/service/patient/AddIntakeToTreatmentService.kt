package com.pada.medmaster.domain.service.patient

import com.pada.medmaster.application.dto.request.treatment.CreateIntakeRequest
import com.pada.medmaster.application.ports.`in`.medicament.ValidateNewIntakeForPatient
import com.pada.medmaster.application.ports.`in`.patient.AddIntakeUseCase
import com.pada.medmaster.application.ports.out.patient.GetPatientPort
import com.pada.medmaster.application.ports.out.patient.UpdatePatientPort
import com.pada.medmaster.domain.service.toDomain
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

// Future refactoring target: Separate Services to handle defined Use Case
// see: https://raatiniemi.se/thoughts/use-case-driven-development/
@Service
class AddIntakeToTreatmentService(
    val getPatientPort: GetPatientPort,
    val updatePatientPort: UpdatePatientPort,
    val validateNewIntakeForPatient: ValidateNewIntakeForPatient
) : AddIntakeUseCase {

    @Transactional
    override fun addIntake(patientId: Long, treatmentId: Long, createIntakeRequest: CreateIntakeRequest) {
        val intake = createIntakeRequest.toDomain()

        val patient = getPatientPort.get(patientId)
        val medicamentsInUse = patient.getMedicamentsInTreatment(treatmentId)
        val patientAddressVoivodeship = patient.getVoivodeship()

        validateNewIntakeForPatient.validate(
            intake.medicamentId!!,
            medicamentsInUse,
            patientAddressVoivodeship
        )

        patient.addIntakeToTreatment(treatmentId, intake)

        updatePatientPort.update(patient)
    }
}