package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import jakarta.persistence.*
import lombok.Builder
import java.time.LocalDateTime

import jakarta.persistence.*

@Entity
class Intake(
    @Id
    @SequenceGenerator(name = "intake_id_sequence", sequenceName = "intake_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "intake_id_sequence")
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicament_id")
    val medicament: Medicament,

    @Enumerated(EnumType.STRING)  // Use @Enumerated to map the enum
    @Column(name = "form")
    val form: IntakeForm,  // No need for @ManyToOne, this is an enum

    @Column(nullable = false)
    val dosage: Int,

    @Enumerated(EnumType.STRING)  // Use @Enumerated to map the enum
    @Column(name = "intakeFrequency")
    val intakeFrequency: IntakeFrequency,

    @OneToMany(mappedBy = "intake", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    val intakeDates: List<IntakeDate> = mutableListOf(),  // Initialize the list

    @Column(nullable = false)
    val intakeLimit: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treatment_id")
    val treatment: Treatment
)
