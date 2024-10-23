package com.pada.medmaster.domain.service

import com.pada.medmaster.application.dto.request.TreatmentRequestDTO
import com.pada.medmaster.application.ports.`in`.CreateTreatmentUseCase
import com.pada.medmaster.application.ports.`in`.GetTreatmentUseCase
import com.pada.medmaster.application.ports.out.CreateTreatmentPort
import com.pada.medmaster.application.ports.out.GetTreatmentPort
import com.pada.medmaster.domain.model.treatment.Intake
import com.pada.medmaster.domain.model.treatment.MedicalProcedure
import com.pada.medmaster.domain.model.treatment.Medicament
import com.pada.medmaster.domain.model.treatment.Treatment
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class TreatmentService(
    val getTreatmentPort: GetTreatmentPort,
    val createTreatmentPort: CreateTreatmentPort
) : GetTreatmentUseCase, CreateTreatmentUseCase {
    override fun getTreatment(code: String): Treatment {
        return getTreatmentPort.getTreatment("Placeholder")
    }

    @Transactional
    override fun createTreatment(treatmentRequestDTO: TreatmentRequestDTO) {
        val medicalProcedures = treatmentRequestDTO.medicalProcedures.map {
            MedicalProcedure(null,it.name,it.description, it.procedureDate,
            it.minimalRecoveryDate, null
        ) }
        val intakes = treatmentRequestDTO.intakes.map { Intake(null,Medicament(null,it.medicament.name, null),
            it.form, it.dosage,it.intakeFrequency,null, it.intakeLimit, null) }
        val treatment = Treatment(
            null,
            treatmentRequestDTO.disease,
            treatmentRequestDTO.description,
            treatmentRequestDTO.code,
            medicalProcedures,
            intakes,
            treatmentRequestDTO.beginDate,
            treatmentRequestDTO.endDate
        )
        createTreatmentPort.createTreatment(treatment)
    }
}