CREATE SEQUENCE IF NOT EXISTS patient_schema.patient_address_id_seq INCREMENT 1 START 1000;

CREATE TABLE IF NOT EXISTS patient_schema.patient_address (
    id BIGINT PRIMARY KEY DEFAULT nextval('patient_schema.patient_address_id_seq'),
    voivodeship VARCHAR(255) NOT NULL,
    district VARCHAR(255) NOT NULL,
    community VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    building_number VARCHAR(50) NOT NULL,
    apartment_number VARCHAR(50) NOT NULL,
    zip_code VARCHAR(20) NOT NULL
);

-- Add address_id column to patient table
ALTER TABLE patient_schema.patient ADD COLUMN address_id BIGINT;

CREATE SEQUENCE IF NOT EXISTS medicament_schema.pharmacy_address_id_seq INCREMENT 1 START 1000;

CREATE TABLE IF NOT EXISTS medicament_schema.pharmacy_address (
    id BIGINT PRIMARY KEY DEFAULT nextval('medicament_schema.pharmacy_address_id_seq'),
    voivodeship VARCHAR(255) NOT NULL,
    district VARCHAR(255) NOT NULL,
    community VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    building_number VARCHAR(50) NOT NULL,
    apartment_number VARCHAR(50) NOT NULL,
    zip_code VARCHAR(20) NOT NULL
);

-- Insert existing address data from pharmacy into pharmacy_address
INSERT INTO medicament_schema.pharmacy_address (id, voivodeship, district, community, location, street, building_number, apartment_number, zip_code)
SELECT id, voivodeship, district, community, location, street, building_number, apartment_number, zip_code FROM medicament_schema.pharmacy;

-- Add address_id column to pharmacy and update references
ALTER TABLE medicament_schema.pharmacy ADD COLUMN address_id BIGINT;
UPDATE medicament_schema.pharmacy SET address_id = id;
ALTER TABLE medicament_schema.pharmacy ADD CONSTRAINT fk_pharmacy_address FOREIGN KEY (address_id) REFERENCES medicament_schema.pharmacy_address (id);


-- Drop old address fields from pharmacy table
ALTER TABLE medicament_schema.pharmacy
DROP COLUMN voivodeship,
DROP COLUMN district,
DROP COLUMN community,
DROP COLUMN location,
DROP COLUMN street,
DROP COLUMN building_number,
DROP COLUMN apartment_number,
DROP COLUMN zip_code;
