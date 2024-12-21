package com.pada.medmaster.application.ports.out

import com.pada.medmaster.domain.model.treatment.Treatment

interface GetActiveTreatmentsByCodePort {

    fun get(code: String): List<Treatment>

}