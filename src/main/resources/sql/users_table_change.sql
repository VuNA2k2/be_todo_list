CREATE TABLE IF NOT EXISTS public.users (
	user_id integer NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1),
	user_name varchar(50) NOT NULL UNIQUE,
	full_name varchar NOT NULL,
	date_of_birth date,
	phone_number varchar(10),
	email varchar NOT NULL,
	avatar varchar,
	CONSTRAINT users_pkey PRIMARY KEY (user_id),
	CONSTRAINT users_fkey FOREIGN KEY (user_id) REFERENCES public.accounts(user_id)
);