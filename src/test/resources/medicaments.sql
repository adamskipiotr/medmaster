INSERT INTO medicament_schema.medicament (id, name, producer, overdose_counteractions) VALUES
(100, 'Medicament100', 'Producer100', 'Overdose Counteractions100'),
(101, 'Medicament101', 'Producer101', 'Overdose Counteractions101'),
(102, 'Medicament102', 'Producer102', 'Overdose Counteractions102'),
(103, 'Medicament103', 'Producer103', 'Overdose Counteractions103'),
(104, 'Medicament104', 'Producer104', 'Overdose Counteractions104');

INSERT INTO medicament_schema.pharmacy_address(id, voivodeship, district, community, location, street, building_number, apartment_number,zip_code)
VALUES
(100, 'Voivodeship100', 'District100', 'Community100', 'Location100', 'Street100', '100', '100A', '12-345'),
(101, 'Voivodeship101', 'District101', 'Community101', 'Location101', 'Street101', '101', '101B', '22-345'),
(102, 'Voivodeship102', 'District102', 'Community102', 'Location102', 'Street102', '102', '102C', '32-345'),
(103, 'Voivodeship103', 'District103', 'Community103', 'Location103', 'Street103', '103', '103D', '42-345'),
(104, 'Voivodeship104', 'District104', 'Community104', 'Location104', 'Street104', '104', '104E', '52-345');

INSERT INTO medicament_schema.pharmacy(id, name, address_id) VALUES
(100, 'Pharmacy100', 100),
(101, 'Pharmacy101', 101),
(102, 'Pharmacy102', 102),
(103, 'Pharmacy103', 103),
(104, 'Pharmacy104', 104);

INSERT INTO medicament_schema.medicament__pharmacies(medicament_id, pharmacy_id) VALUES
(100, 100),
(101, 101),
(102, 102),
(103, 103),
(104, 104);

