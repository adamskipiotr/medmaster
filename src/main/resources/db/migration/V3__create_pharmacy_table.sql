CREATE SEQUENCE IF NOT EXISTS pharmacy_id_seq START 1000 INCREMENT 1;

CREATE TABLE IF NOT EXISTS pharmacy
(
    id BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('pharmacy_id_seq'),
    name VARCHAR(255),
    voivodeship VARCHAR(255),
    district VARCHAR(255),
    community VARCHAR(255),
    location VARCHAR(255),
    street VARCHAR(255),
    building_number VARCHAR(255),
    apartment_number VARCHAR(255),
    zip_code VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS medicament__pharmacies
(
    medicament_id BIGINT,
    pharmacy_id BIGINT
);

ALTER TABLE medicament
ADD COLUMN producer VARCHAR(255),
ADD COLUMN overdose_counteractions VARCHAR(255)
