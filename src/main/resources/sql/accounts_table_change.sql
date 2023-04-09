CREATE TABLE public.accounts (
	user_name varchar(50) UNIQUE,
	password varchar(50),
	user_id integer NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1),
	CONSTRAINT accounts_pkey PRIMARY KEY (user_id)
);

ALTER TABLE public.accounts ALTER COLUMN password SET NOT NULL;

ALTER TABLE public.accounts ALTER COLUMN password TYPE varchar;

ALTER TABLE public.accounts ALTER COLUMN user_id DROP IDENTITY;