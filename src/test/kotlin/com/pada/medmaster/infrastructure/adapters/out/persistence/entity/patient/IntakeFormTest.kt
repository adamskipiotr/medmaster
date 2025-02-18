package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.patient

import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals

class IntakeFormTest {

    @Test
    fun shouldGetEnums() {
        assertEquals(4, IntakeForm.entries.size)
    }
}