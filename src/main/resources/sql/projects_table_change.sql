CREATE TABLE IF NOT EXISTS public.projects (
	id integer NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1),
	user_id integer,
	name varchar NOT NULL,
	description varchar,
	priority integer NOT NULL,
	deadline timestamp NOT NULL,
	status varchar NOT NULL,
	CONSTRAINT projects_pkey PRIMARY KEY (id),
	CONSTRAINT projects_fkey_ref_users FOREIGN KEY (user_id) REFERENCES public.users(user_id)
);

ALTER TABLE public.projects ALTER COLUMN user_id SET NOT NULL;

ALTER TABLE public.projects ALTER COLUMN priority TYPE varchar;

ALTER TABLE public.projects ALTER COLUMN deadline TYPE timestamptz;

ALTER TABLE public.projects ALTER COLUMN status SET DEFAULT 'TODO';

ALTER TABLE public.projects ALTER COLUMN priority SET DEFAULT 'LOW';