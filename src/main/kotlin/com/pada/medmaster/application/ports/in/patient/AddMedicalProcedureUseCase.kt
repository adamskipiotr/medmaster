package com.pada.medmaster.application.ports.`in`.patient

import com.pada.medmaster.application.dto.request.treatment.CreateMedicalProcedureRequest

fun interface AddMedicalProcedureUseCase {
    fun add(patientId: Long, treatmentId: Long, createMedicalProcedureRequest: CreateMedicalProcedureRequest)
}