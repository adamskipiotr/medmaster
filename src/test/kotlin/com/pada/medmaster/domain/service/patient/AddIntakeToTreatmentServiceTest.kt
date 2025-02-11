package com.pada.medmaster.domain.service.patient

import com.pada.medmaster.application.dto.request.treatment.CreateIntakeRequest
import com.pada.medmaster.application.ports.`in`.medicament.ValidateNewIntakeForPatient
import com.pada.medmaster.domain.exception.IngredientProhibitedInPatientCountryException
import com.pada.medmaster.domain.exception.PharmacyNotFoundException
import com.pada.medmaster.domain.service.medicament.ValidateNewIntakeMedicamentService
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.IntakeForm
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.IntakeFrequency
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

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
        val testPatient = getPatientPort.get(1L)
        val createIntakeRequest = createIntakeRequest()

        //when
        sut.addIntake(patientId = 1L, treatmentId = 1L, createIntakeRequest)

        //then
        assertEquals(1, testPatient.treatments[0].intakes.size)
    }

    @Test
    fun shouldThrowPharmacyNotFoundExceptionWhenNewIntakeHasMedicamentNotAvailableInPatientsVoivodeship() {
        //given
        val createIntakeRequest = createIntakeRequest()

        //when
        val exception = assertThrows<PharmacyNotFoundException> {
            sut.addIntake(patientId = 2L, treatmentId = 2L, createIntakeRequest)
        }

        //then
        assertEquals("No pharmacy with medicament Name found in voivodeship: OtherVoivodeship", exception.message)
    }

    @Test
    fun shouldThrowIngredientProhibitedInPatientCountryExceptionWhenNewIntakeHasMedicamentWithIngredientNotAllowedInPatientsCountry() {
        //given
        val createIntakeRequest = createIntakeRequest(2L)

        //when
        val exception = assertThrows<IngredientProhibitedInPatientCountryException> {
            sut.addIntake(patientId = 1L, treatmentId = 1L, createIntakeRequest)
        }

        //then
        assertEquals("Ingredient Second Name is prohibited in Country", exception.message)
    }

    private fun createIntakeRequest(medicamentId: Long = 1L) = CreateIntakeRequest(
        medicamentId, IntakeForm.PILLS, 5,
        IntakeFrequency.ONCE_A_DAY, emptyList(), 6
    )
}