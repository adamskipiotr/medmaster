package com.pada.medmaster.application.ports.out

import com.pada.medmaster.domain.model.treatment.Treatment

interface GetAllTreatmentsPort {
    fun getAllTreatments() : List<Treatment>

}