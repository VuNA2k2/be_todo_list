CREATE TABLE IF NOT EXISTS public.tasks (
	id integer NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1),
	project_id integer NOT NULL,
	title varchar NOT NULL,
	subtitle varchar,
	description varchar,
	priority varchar NOT NULL,
	number_of_pomodoro integer,
	deadline timestamp NOT NULL,
	current_doing_time timestamp,
	reminder bit NOT NULL,
	status varchar NOT NULL,
	CONSTRAINT tasks_pkey PRIMARY KEY (id),
	CONSTRAINT tasks_fkey_ref_projects FOREIGN KEY (project_id) REFERENCES public.projects(id)
);

ALTER TABLE public.tasks ALTER COLUMN deadline TYPE timestamptz;

ALTER TABLE public.tasks ALTER COLUMN current_doing_time TYPE time;

ALTER TABLE public.tasks ALTER COLUMN status SET DEFAULT 'TODO';

ALTER TABLE public.tasks ALTER COLUMN priority SET DEFAULT 'LOW';

ALTER TABLE public.tasks DROP COLUMN IF EXISTS "reminder";

ALTER TABLE public.tasks ADD COLUMN "reminder" boolean NOT NULL DEFAULT false;

ALTER TABLE public.tasks ALTER COLUMN current_doing_time SET DEFAULT '00:00:00';