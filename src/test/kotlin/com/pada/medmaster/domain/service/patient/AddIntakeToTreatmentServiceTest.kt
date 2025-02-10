package com.pada.medmaster.domain.service.patient

import com.pada.medmaster.application.dto.request.treatment.CreateIntakeRequest
import com.pada.medmaster.application.ports.`in`.medicament.ValidateNewIntakeForPatient
import com.pada.medmaster.domain.service.medicament.ValidateNewIntakeMedicamentService
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.IntakeForm
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.IntakeFrequency
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension
import org.junit.jupiter.api.Assertions.*

class AddIntakeToTreatmentServiceTest {

    private val getPatientPort = GetFakePatientPort()
    private val updatePatientPort = UpdateFakePatientPort()
    private val getMedicamentPort = GetFakeMedicamentPort()
    private val getMultipleMedicamentByIdPort = GetMultipleFakeMedicamentPort()
    private val getIngredientsPort = GetFakeIngredientPort()
    private val validateNewIntakeForPatient =
        ValidateNewIntakeMedicamentService(getMedicamentPort, getMultipleMedicamentByIdPort, getIngredientsPort)
    private val sut = AddIntakeToTreatmentService(getPatientPort, updatePatientPort, validateNewIntakeForPatient)


    @Test
    fun shouldAddIntakeToTreatmentWhenValidationPasses() {
        //given
        val createIntakeRequest = CreateIntakeRequest(
            1L, IntakeForm.PILLS, 5,
            IntakeFrequency.ONCE_A_DAY, emptyList(), 6
        )

        //when
        sut.addIntake(patientId = 1L, treatmentId = 1L, createIntakeRequest)

        //then
        assertEquals(1, getPatientPort.get(1L).treatments.get(0).intakes.size)
    }

    @Test
    fun shouldThrowPharmacyNotFoundExceptionWhenNewIntakeHasMedicamentNotAvailableInPatientsVoivodeship() {
        val createIntakeRequest = CreateIntakeRequest(
            1L, IntakeForm.PILLS, 5,
            IntakeFrequency.ONCE_A_DAY, emptyList(), 6
        )

        sut.addIntake(patientId = 1L, treatmentId = 2L, createIntakeRequest)
    }

    @Test
    fun shouldThrowIngredientProhibitedInPatientCountryExceptionWhenNewIntakeHasMedicamentWithIngredientNotAllowedInPatientsCountry() {
        val createIntakeRequest = CreateIntakeRequest(
            1L, IntakeForm.PILLS, 5,
            IntakeFrequency.ONCE_A_DAY, emptyList(), 6
        )

        sut.addIntake(patientId = 1L, treatmentId = 2L, createIntakeRequest)
    }


}