INSERT INTO patient_schema.patient_address(id, country, voivodeship, district, community, location, street, building_number, apartment_number,zip_code)
VALUES
(100, 'Country100', 'Voivodeship100', 'District100', 'Community100', 'Location100', 'Street100', '100', '100A', '12-345'),
(101, 'Country101', 'Voivodeship101', 'District101', 'Community101', 'Location101', 'Street101', '101', '101B', '22-345'),
(102, 'Country102', 'Voivodeship102', 'District102', 'Community102', 'Location102', 'Street102', '102', '102C', '32-345'),
(103, 'Country103', 'Voivodeship103', 'District103', 'Community103', 'Location103', 'Street103', '103', '103D', '42-345'),
(104, 'Country104', 'Voivodeship104', 'District104', 'Community104', 'Location104', 'Street104', '104', '104E', '52-345');

INSERT INTO patient_schema.patient (id, name, last_name, birth_date, gender, address_id)
VALUES
(100, 'Patient100', 'Doe', '1990-05-15', 'XY', 100),
(101, 'Patient101', 'Doe', '1990-05-15', 'XY', 101),
(102, 'Patient102', 'Doe', '1990-05-15', 'XY', 102),
(103, 'Patient103', 'Doe', '1990-05-15', 'XY', 103),
(104, 'Patient104', 'Doe', '1990-05-15', 'XY', 104);