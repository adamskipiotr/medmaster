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

CREATE TABLE IF NOT EXISTS shared_schema.medicament__ingredients
(
    medicament_id BIGINT,
    ingredients_ids BIGINT
);

CREATE TABLE IF NOT EXISTS shared_schema.patient__allergic_ingredients
(
    patient_id BIGINT,
    allergic_ingredient_id BIGINT
);

CREATE TABLE IF NOT EXISTS shared_schema.patient__treatments
(
    patient_id BIGINT,
    treatment_id BIGINT
);

