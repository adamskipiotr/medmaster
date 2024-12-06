package com.pada.medmaster.domain.model.patient

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.Gender
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.SpecialHealthConditions
import java.time.LocalDate

// About bidirectional relationship:
// look at Domain Driven Design of Eric Evans - Chapter 5 - avoiding bidirectional relationship if there is no such need
class Patient(
    val id: Long? = null,
    val name: String,
    var lastName: String,
    var birthDate: LocalDate,
    var specialHealthConditions: MutableList<SpecialHealthConditions> = mutableListOf(),
    var gender: Gender,
)
