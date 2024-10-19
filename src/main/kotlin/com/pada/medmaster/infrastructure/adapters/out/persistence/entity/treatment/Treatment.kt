package com.pada.medmaster.infrastructure.adapters.out.persistence.entity.treatment

import java.time.LocalDateTime
import jakarta.persistence.*
import lombok.Builder

@Builder
@Entity
@Table(name = "treatment")
class Treatment(
    @Id
    @SequenceGenerator(name = "treatment_id_sequence", sequenceName = "treatment_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "treatment_id_sequence")
    var id: Long = 0,
    val disease: String,
    val description: String,
    @OneToMany(mappedBy = "treatment", cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY, orphanRemoval = true)
    @Builder.Default
    val medicalProcedures: List<MedicalProcedure>,
    @OneToMany(mappedBy = "treatment", cascade = [CascadeType.ALL],
        fetch = FetchType.LAZY, orphanRemoval = true)
    @Builder.Default
    val intakes: List<Intake>,
    val beginDate: LocalDateTime,
    val endDate: LocalDateTime
) {


}