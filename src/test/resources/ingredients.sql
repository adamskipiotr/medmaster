INSERT INTO medicament_schema.ingredient (id, name) VALUES
(100, 'Ingredient100'),
(101, 'Ingredient101'),
(102, 'Ingredient102'),
(103, 'Ingredient103'),
(104, 'Ingredient104');

INSERT INTO shared_schema.medicament__ingredients(medicament_id, ingredients_ids) VALUES
(100, 100);
