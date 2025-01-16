package com.pada.medmaster.application.ports.out

import com.pada.medmaster.domain.model.treatment.Treatment

interface UpdateTreatmentPort {
    fun update(treatment: Treatment)

}