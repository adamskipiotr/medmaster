CREATE SCHEMA IF NOT EXISTS treatment_schema;
CREATE SCHEMA IF NOT EXISTS medicament_schema;
CREATE SCHEMA IF NOT EXISTS patient_schema;
CREATE SCHEMA IF NOT EXISTS ingredient_schema;
CREATE SCHEMA IF NOT EXISTS shared_schema;

CREATE TYPE treatment_schema.intake_form as ENUM ('PILLS','SHOT','LIQUID', 'OINTMENT');

CREATE TYPE treatment_schema.intake_frequency as ENUM ('HOURLY','TWICE_A_DAY','THREE_TIMES_A_DAY', 'ONCE_A_DAY');
