package com.pada.medmaster.application.ports.`in`

import com.pada.medmaster.domain.model.treatment.Treatment

interface GetAllTreatmentsUseCase {
    fun execute(): List<Treatment>
}