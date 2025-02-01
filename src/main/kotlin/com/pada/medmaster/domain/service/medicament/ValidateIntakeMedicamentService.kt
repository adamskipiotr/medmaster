package com.pada.medmaster.domain.service.medicament

import com.pada.medmaster.application.ports.`in`.medicament.ValidateIntakeMedicamentUseCase
import com.pada.medmaster.application.ports.out.medicament.GetMedicamentPort
import com.pada.medmaster.application.ports.out.medicament.GetMultipleMedicamentByIdPort
import org.springframework.stereotype.Service

@Service
class ValidateIntakeMedicamentService(private val getMedicamentPort: GetMedicamentPort,
                                      private val getMultipleMedicamentByIdPort: GetMultipleMedicamentByIdPort) :
    ValidateIntakeMedicamentUseCase {



    override fun validateNewMedicamentNotConflictingWithInUse(medicamentId: Long, inUseMedicamentIds: List<Long?>) {
        val inUseMedicament = getMultipleMedicamentByIdPort.get(inUseMedicamentIds)
        val treatmentNewMedicament = getMedicamentPort.get(medicamentId)
        inUseMedicament.forEach{medicament -> medicament.validateSafeWithNewMedicament(treatmentNewMedicament) }
    }

}
