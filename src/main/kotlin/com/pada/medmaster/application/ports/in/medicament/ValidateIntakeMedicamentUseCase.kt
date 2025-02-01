package com.pada.medmaster.application.ports.`in`.medicament

interface ValidateIntakeMedicamentUseCase {
    fun validateNewMedicamentNotConflictingWithInUse(medicamentId: Long, inUseMedicamentIds: List<Long?>)

}
