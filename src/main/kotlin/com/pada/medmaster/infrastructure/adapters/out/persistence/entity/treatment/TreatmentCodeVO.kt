package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import com.pada.medmaster.domain.model.treatment.Treatment
import com.pada.medmaster.domain.model.treatment.TreatmentCode
import jakarta.persistence.*

// Value Object: Treatment Code
@Embeddable
class TreatmentCodeVO {
    lateinit var code: String
    var maximalActiveTreatments: Int = 0

    fun asDomain() = TreatmentCode(
        code,
        maximalActiveTreatments
    )
}
