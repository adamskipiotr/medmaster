package com.pada.medmaster.application.ports.`in`

import com.pada.medmaster.domain.model.patient.Treatment

interface GetAllTreatmentsUseCase {
    fun execute(): List<Treatment>
}