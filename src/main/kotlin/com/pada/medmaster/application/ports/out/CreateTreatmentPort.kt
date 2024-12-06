package com.pada.medmaster.application.ports.out

import com.pada.medmaster.domain.model.treatment.Treatment

interface CreateTreatmentPort {
    fun createTreatment(treatment: Treatment): Treatment

}