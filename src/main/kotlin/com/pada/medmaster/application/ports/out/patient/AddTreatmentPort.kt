package com.pada.medmaster.application.ports.out.patient

import com.pada.medmaster.domain.model.patient.Treatment

interface AddTreatmentPort {
    fun execute(id: Long,treatment: Treatment)

}