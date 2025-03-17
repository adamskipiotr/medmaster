package com.pada.medmaster.domain.service.patient.stubs

import com.pada.medmaster.application.ports.out.patient.PublishIntakeMissedEventPort
import com.pada.medmaster.domain.events.MissedIntakeEvent

class PublishIntakeMissedEventPortStub : PublishIntakeMissedEventPort {

    override fun publish(missedIntakes: List<MissedIntakeEvent>) {
        println("Publish Intake Missed Event Stub")
    }
}
