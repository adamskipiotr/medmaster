package com.pada.medmaster.application.ports.out.treatment

import com.pada.medmaster.domain.model.treatment.Treatment

interface AddIntakePort {
    fun addIntake(treatment: Treatment)

}