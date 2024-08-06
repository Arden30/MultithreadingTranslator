create table if not exists translation
(
    id              bigint generated always as identity,
    ip              text not null,
    source_text     text,
    target_text     text,

    unique (id),
    primary key (id)
)