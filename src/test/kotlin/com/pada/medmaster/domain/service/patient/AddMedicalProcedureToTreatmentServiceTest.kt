package com.pada.medmaster.domain.service.patient

import com.pada.medmaster.application.dto.request.treatment.CreateIntakeRequest
import com.pada.medmaster.domain.exception.IncompatibleMedicamentException
import com.pada.medmaster.domain.exception.IngredientProhibitedInPatientCountryException
import com.pada.medmaster.domain.exception.PharmacyNotFoundException
import com.pada.medmaster.domain.service.medicament.ValidateNewIntakeMedicamentService
import com.pada.medmaster.domain.service.patient.stubs.*
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.IntakeForm
import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient.IntakeFrequency
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AddMedicalProcedureToTreatmentServiceTest {

    private val getPatientPort = GetPatientPortStub()
    private val updatePatientPort = UpdatePatientPortStub()
    private val sut = AddMedicalProcedureToTreatmentService(getPatientPort, updatePatientPort)

    @Test
    fun shouldAddIntakeToTreatmentWhenValidationPasses() {
        //given
        val testPatient = getPatientPort.get(1L)
        val createIntakeRequest = createIntakeRequest()

        //when
        sut.add(patientId = 1L, treatmentId = 1L, createIntakeRequest)

        //then
        assertEquals(2, testPatient.treatments[0].intakes.size)
    }

    @Test
    fun shouldThrowPharmacyNotFoundExceptionWhenNewIntakeHasMedicamentNotAvailableInPatientsVoivodeship() {
        //given
        val createIntakeRequest = createIntakeRequest()

        //when
        val exception = assertThrows<PharmacyNotFoundException> {
            sut.add(patientId = 2L, treatmentId = 2L, createIntakeRequest)
        }

        //then
        assertEquals("No pharmacy with medicament Medicament 1 found in voivodeship: OtherVoivodeship", exception.message)
    }

    @Test
    fun shouldThrowIngredientProhibitedInPatientCountryExceptionWhenNewIntakeHasMedicamentWithIngredientNotAllowedInPatientsCountry() {
        //given
        val createIntakeRequest = createIntakeRequest(2L)

        //when
        val exception = assertThrows<IngredientProhibitedInPatientCountryException> {
            sut.add(patientId = 1L, treatmentId = 1L, createIntakeRequest)
        }

        //then
        assertEquals("Ingredient Second Name is prohibited in Country", exception.message)
    }

    @Test
    fun shouldThrowIncompatibleMedicamentExceptionWhenNewIntakeHasMedicamentNotAllowedToMixWithMedicamentInUse() {
        //given
        val createIntakeRequest = createIntakeRequest(4L)

        //when
        val exception = assertThrows<IncompatibleMedicamentException> {
            sut.add(patientId = 1L, treatmentId = 1L, createIntakeRequest)
        }

        //then
        assertEquals("Medicament 4 can't be used together with Medicament 3 - incompatible Ingredients", exception.message)
    }

    private fun createIntakeRequest(medicamentId: Long = 1L) = CreateIntakeRequest(
        medicamentId, IntakeForm.PILLS, 5,
        IntakeFrequency.ONCE_A_DAY, emptyList(), 6
    )


}