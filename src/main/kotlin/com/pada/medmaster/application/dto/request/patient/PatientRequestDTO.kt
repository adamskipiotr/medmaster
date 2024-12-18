package com.pada.medmaster.application.dto.request.patient

import com.pada.medmaster.domain.model.medicament.Medicament
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.Gender
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.SpecialHealthConditions
import org.jetbrains.annotations.NotNull
import java.time.LocalDate

class PatientRequestDTO(
    val name: String,
    var lastName: String,
    var birthDate: LocalDate,
    var specialHealthConditions: MutableList<SpecialHealthConditions> = mutableListOf(),
    var gender: Gender
    )