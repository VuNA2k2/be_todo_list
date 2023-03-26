CREATE TABLE IF NOT EXISTS public.notes(
	id integer NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1),
	project_id integer NOT NULL,
	title varchar NOT NULL,
	subtitle varchar,
	description varchar,
	CONSTRAINT notes_pkey PRIMARY KEY (id),
	CONSTRAINT notes_fkey_ref_projects FOREIGN KEY (project_id) REFERENCES public.projects(id)
);