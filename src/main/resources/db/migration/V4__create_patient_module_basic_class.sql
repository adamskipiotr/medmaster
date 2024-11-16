CREATE SEQUENCE IF NOT EXISTS patient_id_seq START 1000 INCREMENT 1;

CREATE TABLE IF NOT EXISTS patient
(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('patient_id_seq'),
    name VARCHAR(255),
    last_name VARCHAR(255),
    birth_date TIMESTAMP(6)
);
