package com.pada.medmaster.application.ports.out.patient

import com.pada.medmaster.domain.model.patient.Patient

fun interface GetPatientPort {
    fun get(id: Long): Patient
}