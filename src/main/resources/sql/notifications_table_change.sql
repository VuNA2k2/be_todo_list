CREATE TABLE IF NOT EXISTS public.notifications (
	id integer NOT NULL GENERATED ALWAYS AS IDENTITY (INCREMENT 1 START 1),
	task_id integer NOT NULL,
	title varchar NOT NULL,
	subtitle varchar,
	content varchar,
	time_stamp timestamp NOT NULL,
	CONSTRAINT notifications_pkey PRIMARY KEY (id),
	CONSTRAINT notifications_fkey_ref_tasks FOREIGN KEY (task_id) REFERENCES public.tasks(id)
);