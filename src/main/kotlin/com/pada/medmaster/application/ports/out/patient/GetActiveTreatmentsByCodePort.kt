package com.pada.medmaster.application.ports.out.patient

import com.pada.medmaster.domain.model.patient.Treatment

interface GetActiveTreatmentsByCodePort {

    fun get(code: String): List<Treatment>

}