CREATE SEQUENCE IF NOT EXISTS patient_schema.treatment_id_seq START 1000 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS medicament_schema.medicament_id_seq START 1000 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS patient_schema.medical_procedure_id_seq START 1000 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS patient_schema.intake_id_seq START 1000 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS medicament_schema.ingredient_id_seq START 1000 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS medicament_schema.country_id_seq START 1000 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS patient_schema.intake_date_id_seq START 1000 INCREMENT 1;

CREATE TABLE IF NOT EXISTS medicament_schema.country
(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('medicament_schema.country_id_seq'),
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS medicament_schema.medicament
(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('medicament_schema.medicament_id_seq'),
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS medicament_schema.ingredient
(
    id  BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('medicament_schema.ingredient_id_seq'),
    name VARCHAR(255)
);

CREATE TABLE medicament_schema.ingredient_incompatibilities (
    ingredient_id BIGINT NOT NULL,
    incompatible_ingredient_id BIGINT NOT NULL,
    PRIMARY KEY (ingredient_id, incompatible_ingredient_id),
    CONSTRAINT fk_ingredient FOREIGN KEY (ingredient_id)
        REFERENCES medicament_schema.ingredient(id) ON DELETE CASCADE,
    CONSTRAINT fk_incompatible_ingredient FOREIGN KEY (incompatible_ingredient_id)
        REFERENCES medicament_schema.ingredient(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS medicament_schema.ingredient__prohibiting_country
(
    ingredient_id BIGINT,
    country_id BIGINT
);


CREATE TABLE IF NOT EXISTS shared_schema.medicament__ingredients
(
    medicament_id BIGINT,
    ingredients_ids BIGINT
);