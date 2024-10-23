CREATE SEQUENCE IF NOT EXISTS treatment_id_seq START 1000 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS medicament_id_seq START 1000 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS medical_procedure_id_seq START 1000 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS intake_id_seq START 1000 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS ingredient_id_seq START 1000 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS country_id_seq START 1000 INCREMENT 1;
CREATE SEQUENCE IF NOT EXISTS intake_date_id_seq START 1000 INCREMENT 1;

CREATE TABLE IF NOT EXISTS country
(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('country_id_seq'),
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS intake_date
(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('intake_date_id_seq'),
    "date" TIMESTAMP(6),
    intake_id BIGINT
);

CREATE TABLE IF NOT EXISTS treatment
(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('treatment_id_seq'),
    disease VARCHAR(255),
    code VARCHAR(255),
    description VARCHAR(255),
    begin_date TIMESTAMP(6),
    end_date TIMESTAMP(6)
);

CREATE TABLE IF NOT EXISTS medicament
(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('medicament_id_seq'),
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS medical_procedure
(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('medical_procedure_id_seq'),
    name VARCHAR(255),
    description VARCHAR(255),
    procedure_date  TIMESTAMP(6),
    minimal_recovery_date TIMESTAMP(6),
    treatment_id BIGINT
);

CREATE TABLE IF NOT EXISTS intake (
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('intake_id_seq'),
    medicament_id BIGINT,
    CONSTRAINT fk_medicament FOREIGN KEY (medicament_id) REFERENCES medicament(id),
    form VARCHAR(255) NOT NULL,
    dosage INT NOT NULL,
    intake_frequency VARCHAR(255) NOT NULL,
    intake_limit INT NOT NULL,
    treatment_id BIGINT,
    CONSTRAINT fk_treatment FOREIGN KEY (treatment_id) REFERENCES treatment(id)
);


CREATE TABLE IF NOT EXISTS ingredient
(
    id  BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('ingredient_id_seq'),
    name VARCHAR(255),
    medicament_id BIGINT,  -- Foreign key for the medicament
    parent_id BIGINT,
    ingredient__prohibiting_country BIGINT,
    CONSTRAINT fk_medicament FOREIGN KEY (medicament_id) REFERENCES medicament(id)
);

CREATE TABLE IF NOT EXISTS ingredient__prohibiting_country
(
    ingredient_id BIGINT,
    country_id BIGINT
);

