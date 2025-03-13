package com.pada.medmaster.domain.service.patient

import com.pada.medmaster.application.ports.out.patient.GetPatientsPort
import com.pada.medmaster.application.ports.out.patient.PublishIntakeMissedEventPort
import com.pada.medmaster.application.ports.out.patient.UpdatePatientPort
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ScheduledIntakeValidationService(
    val getPatientsPort: GetPatientsPort,
    val updatePatientPort: UpdatePatientPort,
    val publishIntakeMissedEventPort : PublishIntakeMissedEventPort
)  {

    @Transactional
    fun validateIntakes() {
        val patients = getPatientsPort.get()
        val firstPatient = patients.first()

        val missedIntakes =  firstPatient.validateIntakes()
        publishIntakeMissedEventPort.publish(missedIntakes)


    }
}