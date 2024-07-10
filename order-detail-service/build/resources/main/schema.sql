CREATE TABLE if not exists public.client (
    cli_UUID uuid NOT NULL default gen_random_uuid(),
    cli_name varchar NOT NULL,
    cli_last_name varchar NOT NULL,
    CONSTRAINT client_pk PRIMARY KEY (cli_UUID)
);

CREATE TABLE if not exists public.product (
      pro_UUID uuid NOT NULL default gen_random_uuid(),
      pro_code varchar NOT NULL,
      pro_name varchar NOT NULL,
      CONSTRAINT product_pk PRIMARY KEY (pro_UUID)
);

CREATE TABLE if not exists public.order_header (
    ord_UUID uuid NOT NULL default gen_random_uuid(),
    cli_UUID uuid NOT NULL,
    ord_code varchar NOT NULL,
    ord_date timestamp NOT NULL,
    CONSTRAINT ord_pk PRIMARY KEY (ord_UUID),
    CONSTRAINT cli_fkey FOREIGN KEY (cli_UUID) REFERENCES public.client(cli_UUID)
);

CREATE TABLE if not exists public.order_detail (
    odt_UUID uuid NOT NULL default gen_random_uuid(),
    ord_UUID uuid NOT NULL,
    pro_UUID uuid NOT NULL,
    odt_unit_price numeric(38, 2) NOT NULL,
    CONSTRAINT odt_pk PRIMARY KEY (odt_UUID),
    CONSTRAINT ord_fkey FOREIGN KEY (ord_UUID) REFERENCES public.order_header(ord_UUID),
    CONSTRAINT pro_fkey FOREIGN KEY (pro_UUID) REFERENCES public.product(pro_UUID)
);