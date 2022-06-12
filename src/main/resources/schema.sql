create table if not exists pokemon (
    id     bigint generated always as identity ,
    name   varchar(25),
    weight integer
);