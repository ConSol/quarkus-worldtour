CREATE TABLE public.color (
    id BIGINT CONSTRAINT color_pk_id PRIMARY KEY,
    name VARCHAR(255) NOT NULL CONSTRAINT color_unique_name UNIQUE,
    hex_code VARCHAR(255)
);

CREATE SEQUENCE color_seq_id OWNED BY public.color.id;