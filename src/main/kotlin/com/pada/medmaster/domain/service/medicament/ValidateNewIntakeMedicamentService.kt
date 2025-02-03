package com.pada.medmaster.domain.service.medicament

import com.pada.medmaster.application.ports.`in`.medicament.ValidateNewIntakeForPatient
import com.pada.medmaster.application.ports.out.medicament.GetMedicamentPort
import com.pada.medmaster.application.ports.out.medicament.GetMultipleMedicamentByIdPort
import org.springframework.stereotype.Service

@Service
class ValidateNewIntakeMedicamentService(private val getMedicamentPort: GetMedicamentPort,
                                         private val getMultipleMedicamentByIdPort: GetMultipleMedicamentByIdPort) :
    ValidateNewIntakeForPatient {



    override fun validate(medicamentId: Long, inUseMedicamentIds: List<Long?>, patientAddressVoivodeship: String?) {
        val inUseMedicament = getMultipleMedicamentByIdPort.get(inUseMedicamentIds)
        val treatmentNewMedicament = getMedicamentPort.get(medicamentId)
        treatmentNewMedicament.validateIsInVoivodeship(patientAddressVoivodeship)
        inUseMedicament.forEach{medicament -> medicament.validateSafeWithNewMedicament(treatmentNewMedicament) }
    }

}
