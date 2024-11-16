package com.pada.medmaster.domain.model.patient

import com.pada.medmaster.domain.model.medicament.Medicament
import com.pada.medmaster.domain.model.pharmacy.Pharmacy
import org.springframework.boot.availability.ApplicationAvailability
import java.time.LocalDate

// About bidirectional relationship:
// look at Domain Driven Design of Eric Evans - Chapter 5 - avoiding bidirectional relationship if there is no such need
class Patient(
    val id: Long? = null,
    val name: String,
    var lastName: String,
    var birthDate: LocalDate,
    var medicaments: MutableList<Medicament> = mutableListOf(),  // Initialize to empty list
) {

}
