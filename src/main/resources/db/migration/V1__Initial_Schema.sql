CREATE TABLE program (
    id                  BIGSERIAL PRIMARY KEY NOT NULL,
    kode_program        varchar(255) NOT NULL,
    nama_program        varchar(255) NOT NULL,
    status              varchar(255) NOT NULL,
    created_date        timestamp NOT NULL default now(),
    last_modified_date  timestamp NOT NULL default now(),
    version             integer NOT NULL default 0
);