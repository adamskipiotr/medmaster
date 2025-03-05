package com.pada.medmaster.domain.service.patient

import com.pada.medmaster.application.dto.request.patient.ReportIntakeRequest
import com.pada.medmaster.application.ports.`in`.patient.ReportIntakeUseCase
import com.pada.medmaster.application.ports.out.patient.GetPatientPort
import com.pada.medmaster.application.ports.out.patient.UpdatePatientPort
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

// Future refactoring target: Separate Services to handle defined Use Case
// see: https://raatiniemi.se/thoughts/use-case-driven-development/
@Service
class ReportIntakeService(
    val getPatientPort: GetPatientPort,
    val updatePatientPort: UpdatePatientPort,
) : ReportIntakeUseCase {

    @Transactional
    override fun report(patientId: Long, treatmentId: Long, intakeId:Long, reportIntakeRequest: ReportIntakeRequest) {
        val patient = getPatientPort.get(patientId)
        patient.reportIntake(treatmentId, intakeId, reportIntakeRequest)

        updatePatientPort.update(patient)
    }
}