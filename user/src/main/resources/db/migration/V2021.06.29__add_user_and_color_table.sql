CREATE TABLE public.color (
    id BIGINT CONSTRAINT color_pk_id PRIMARY KEY,
    name VARCHAR(255) NOT NULL CONSTRAINT color_unique_name UNIQUE
);

CREATE SEQUENCE color_seq_id OWNED BY public.color.id;

CREATE TABLE public.user (
    id BIGINT CONSTRAINT user_pk_id PRIMARY KEY,
    username VARCHAR(255) NOT NULL CONSTRAINT user_unique_name UNIQUE,
    birth_date DATE NOT NULL,
    fk_favorite_color BIGINT REFERENCES color(id)
);

CREATE SEQUENCE user_seq_id OWNED BY public.user.id;