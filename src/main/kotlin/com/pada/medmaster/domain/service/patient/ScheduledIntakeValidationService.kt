package com.pada.medmaster.domain.service.patient

import com.pada.medmaster.application.ports.out.patient.GetPatientsPort
import com.pada.medmaster.application.ports.out.patient.PublishIntakeMissedEventPort
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.Clock

@Service
class ScheduledIntakeValidationService(
    val getPatientsPort: GetPatientsPort,
    val publishIntakeMissedEventPort: PublishIntakeMissedEventPort,
    private val clock: Clock
) {

    @Transactional
    fun validateIntakes() {
        val patients = getPatientsPort.get()

        patients.forEach { patient ->
            val missedIntakes = patient.validateIntakes(clock)
            publishIntakeMissedEventPort.publish(missedIntakes)
        }
    }
}