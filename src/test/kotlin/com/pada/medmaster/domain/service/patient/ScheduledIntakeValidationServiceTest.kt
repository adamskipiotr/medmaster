package com.pada.medmaster.domain.service.patient

import com.pada.medmaster.domain.service.patient.stubs.GetPatientsPortStub
import com.pada.medmaster.domain.service.patient.stubs.PublishIntakeMissedEventPortStub
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ScheduledIntakeValidationServiceTest {

    private val getPatientsPort = GetPatientsPortStub()
    private val publishIntakeMissedEventPort = PublishIntakeMissedEventPortStub()

    private val sut = ScheduledIntakeValidationService(getPatientsPort, publishIntakeMissedEventPort)

    @Test
    fun shouldWhen() {


    }


}