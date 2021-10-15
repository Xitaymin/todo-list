create sequence if not exists global_sec start with 1;

create table if not exists tasks (
    id integer primary key default nextval('global_sec'),
    text varchar
)