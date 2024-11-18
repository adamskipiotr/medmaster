package com.pada.medmaster.infrastructure.adapters.`in`.rest

import com.pada.medmaster.application.dto.request.medicament.MedicamentRequestDTO
import com.pada.medmaster.application.dto.request.patient.PatientRequestDTO
import com.pada.medmaster.application.ports.`in`.CreateMedicamentUseCase
import com.pada.medmaster.application.ports.`in`.CreatePatientUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/patients")
class PatientController(
    private val createPatientUseCase: CreatePatientUseCase
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPatient(@RequestBody patientRequestDTO: PatientRequestDTO) {
        createPatientUseCase.create(patientRequestDTO)
    }
}
