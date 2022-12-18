create table if not exists product
(
    id            bigserial
        constraint product_pk
            primary key,
    product_name  varchar(200)     not null,
    price         double precision not null,
    is_promoted   boolean          not null,
    creation_date timestamp(6)     not null,
    is_deleted    boolean          not null
);

alter table product
    owner to postgres;

create unique index if not exists product_id_uindex
    on product (id);

create table if not exists discount_card
(
    id            bigserial
        constraint discount_card_pk
            primary key,
    holder_name   varchar(30),
    holder_email  varchar(30)      not null,
    discount_rate double precision not null,
    is_active     boolean          not null,
    creation_date timestamp(6)     not null
);

alter table discount_card
    owner to postgres;

create unique index if not exists discount_card_id_uindex
    on discount_card (id);

