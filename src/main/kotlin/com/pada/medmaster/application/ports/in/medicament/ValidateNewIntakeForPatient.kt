package com.pada.medmaster.application.ports.`in`.medicament

fun interface ValidateNewIntakeForPatient {
    fun validate(medicamentId: Long, inUseMedicamentIds: List<Long?>, patientAddressVoivodeship: String?, patientAddressCountry: String?)

}
