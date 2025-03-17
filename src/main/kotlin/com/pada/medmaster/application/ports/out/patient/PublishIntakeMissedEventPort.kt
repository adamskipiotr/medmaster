package com.pada.medmaster.application.ports.out.patient

import com.pada.medmaster.domain.events.MissedIntakeEvent

fun interface PublishIntakeMissedEventPort {
    fun publish(missedIntakes: List<MissedIntakeEvent>)
}