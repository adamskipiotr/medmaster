CREATE SEQUENCE IF NOT EXISTS patient_schema.patient_id_seq START 1000 INCREMENT 1;

CREATE TABLE IF NOT EXISTS patient_schema.patient
(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('patient_schema.patient_id_seq'),
    name VARCHAR(255),
    last_name VARCHAR(255),
    birth_date DATE,
    gender VARCHAR(255) NOT NULL
);

CREATE TABLE patient_schema.patient_special_health_conditions (
    patient_id BIGINT NOT NULL,
    special_health_conditions VARCHAR(50) NOT NULL,
    PRIMARY KEY (patient_id, special_health_conditions),
    FOREIGN KEY (patient_id) REFERENCES patient_schema.patient(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS patient_schema.treatment
(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('patient_schema.treatment_id_seq'),
    disease VARCHAR(255),
    code VARCHAR(255),
    description VARCHAR(255),
    begin_date TIMESTAMP(6),
    end_date TIMESTAMP(6),
    patient_id BIGINT,
    CONSTRAINT fk_patient FOREIGN KEY (patient_id) REFERENCES patient_schema.patient(id)
);

CREATE TABLE IF NOT EXISTS patient_schema.intake_date
(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('patient_schema.intake_date_id_seq'),
    "date" TIMESTAMP(6),
    intake_id BIGINT
);

CREATE TABLE IF NOT EXISTS patient_schema.medical_procedure
(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('patient_schema.medical_procedure_id_seq'),
    name VARCHAR(255),
    description VARCHAR(255),
    procedure_date  TIMESTAMP(6),
    minimal_recovery_date TIMESTAMP(6),
    treatment_id BIGINT
);

CREATE TABLE IF NOT EXISTS patient_schema.intake (
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('patient_schema.intake_id_seq'),
    medicament_id BIGINT,
    CONSTRAINT fk_medicament FOREIGN KEY (medicament_id) REFERENCES medicament_schema.medicament(id),
    form VARCHAR(255) NOT NULL,
    dosage INT NOT NULL,
    intake_frequency VARCHAR(255) NOT NULL,
    intake_limit INT NOT NULL,
    treatment_id BIGINT,
    CONSTRAINT fk_treatment FOREIGN KEY (treatment_id) REFERENCES patient_schema.treatment(id)
);


CREATE TABLE IF NOT EXISTS shared_schema.patient__allergic_ingredients
(
    patient_id BIGINT,
    allergic_ingredient_id BIGINT
);
