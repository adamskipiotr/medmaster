package com.pada.medmaster.application.ports.out.treatment

import com.pada.medmaster.domain.model.patient.Treatment

interface GetAllTreatmentsPort {
    fun getAllTreatments(): List<Treatment>

}