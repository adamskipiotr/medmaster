CREATE SEQUENCE config_id_seq START 1000 INCREMENT 1;

CREATE TABLE IF NOT EXISTS treatment
(
    id  BIGINT NOT NULL PRIMARY KEY DEFAULT nextval('config_id_seq')
);