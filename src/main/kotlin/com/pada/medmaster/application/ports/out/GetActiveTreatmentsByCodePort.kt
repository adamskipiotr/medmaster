package com.pada.medmaster.application.ports.out

import com.pada.medmaster.domain.model.treatment.Treatment
import com.pada.medmaster.domain.model.treatment.TreatmentCode

interface GetActiveTreatmentsByCodePort {

    fun get(code: TreatmentCode): List<Treatment>

}