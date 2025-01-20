CREATE SEQUENCE IF NOT EXISTS treatment_schema.treatment_id_seq START 1000 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS medicament_schema.medicament_id_seq START 1000 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS treatment_schema.medical_procedure_id_seq START 1000 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS treatment_schema.intake_id_seq START 1000 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS ingredient_schema.ingredient_id_seq START 1000 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS ingredient_schema.country_id_seq START 1000 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS treatment_schema.intake_date_id_seq START 1000 INCREMENT 1;

CREATE TABLE IF NOT EXISTS ingredient_schema.country
(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('ingredient_schema.country_id_seq'),
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS treatment_schema.intake_date
(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('treatment_schema.intake_date_id_seq'),
    "date" TIMESTAMP(6),
    intake_id BIGINT
);

CREATE TABLE IF NOT EXISTS treatment_schema.treatment
(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('treatment_schema.treatment_id_seq'),
    disease VARCHAR(255),
    code VARCHAR(255),
    description VARCHAR(255),
    begin_date TIMESTAMP(6),
    end_date TIMESTAMP(6)
);

CREATE TABLE IF NOT EXISTS medicament_schema.medicament
(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('medicament_schema.medicament_id_seq'),
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS treatment_schema.medical_procedure
(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('treatment_schema.medical_procedure_id_seq'),
    name VARCHAR(255),
    description VARCHAR(255),
    procedure_date  TIMESTAMP(6),
    minimal_recovery_date TIMESTAMP(6),
    treatment_id BIGINT
);

CREATE TABLE IF NOT EXISTS treatment_schema.intake (
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('treatment_schema.intake_id_seq'),
    medicament_id BIGINT,
    CONSTRAINT fk_medicament FOREIGN KEY (medicament_id) REFERENCES medicament_schema.medicament(id),
    form VARCHAR(255) NOT NULL,
    dosage INT NOT NULL,
    intake_frequency VARCHAR(255) NOT NULL,
    intake_limit INT NOT NULL,
    treatment_id BIGINT,
    CONSTRAINT fk_treatment FOREIGN KEY (treatment_id) REFERENCES treatment_schema.treatment(id)
);


CREATE TABLE IF NOT EXISTS ingredient_schema.ingredient
(
    id  BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('ingredient_schema.ingredient_id_seq'),
    name VARCHAR(255),
    medicament_id BIGINT,  -- Foreign key for the medicament
    ingredient__prohibiting_country BIGINT,
    CONSTRAINT fk_medicament FOREIGN KEY (medicament_id) REFERENCES medicament_schema.medicament(id)
);

CREATE TABLE ingredient_schema.ingredient_incompatibilities (
    ingredient_id BIGINT NOT NULL,
    incompatible_ingredient_id BIGINT NOT NULL,
    PRIMARY KEY (ingredient_id, incompatible_ingredient_id),
    CONSTRAINT fk_ingredient FOREIGN KEY (ingredient_id)
        REFERENCES ingredient_schema.ingredient(id) ON DELETE CASCADE,
    CONSTRAINT fk_incompatible_ingredient FOREIGN KEY (incompatible_ingredient_id)
        REFERENCES ingredient_schema.ingredient(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ingredient_schema.ingredient__prohibiting_country
(
    ingredient_id BIGINT,
    country_id BIGINT
);

