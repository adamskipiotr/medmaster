package com.pada.medmaster.domain.model.treatment

import com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment.IntakeEntity
import java.time.LocalDateTime

class IntakeDate(
    var id: Long = 0,
    val date: LocalDateTime,
    val intake: Intake
)