CREATE SEQUENCE IF NOT EXISTS patient_id_seq START 1000 INCREMENT 1;

CREATE TABLE IF NOT EXISTS patient
(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('patient_id_seq'),
    name VARCHAR(255),
    last_name VARCHAR(255),
    birth_date DATE
);

CREATE TABLE IF NOT EXISTS medicament__ingredients
(
    medicament_id BIGINT,
    ingredients_ids BIGINT
);

CREATE TABLE IF NOT EXISTS patient__allergic_ingredients
(
    patient_id BIGINT,
    allergic_ingredient_id BIGINT
);

CREATE TABLE IF NOT EXISTS patient__treatments
(
    patient_id BIGINT,
    treatment_id BIGINT
);

