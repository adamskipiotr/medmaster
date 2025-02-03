package com.pada.medmaster.application.ports.`in`.medicament

interface ValidateNewIntakeForPatient {
    fun validate(medicamentId: Long, inUseMedicamentIds: List<Long?>, patientAddressVoivodeship: String?)

}
