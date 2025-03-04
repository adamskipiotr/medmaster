ALTER TABLE patient_schema.intake_date
ADD COLUMN expected_date_min_gap TIMESTAMP(6),
ADD COLUMN expected_date_max_gap TIMESTAMP(6),
ADD COLUMN intake_in_time_gap BOOLEAN,
ADD COLUMN overdose BOOLEAN

