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
      pro_stock numeric not null,
      CONSTRAINT product_pk PRIMARY KEY (pro_UUID)
);

CREATE TABLE if not exists public.order_header (
       ord_UUID uuid NOT NULL default gen_random_uuid(),
       cli_UUID uuid NOT NULL,
       ord_code varchar NOT NULL,
       ord_date date NOT NULL,
       CONSTRAINT ord_pk PRIMARY KEY (ord_UUID),
       CONSTRAINT cli_fkey FOREIGN KEY (cli_UUID) REFERENCES public.client(cli_UUID)
);

CREATE TABLE if not exists public.order_detail (
       odt_UUID uuid NOT NULL default gen_random_uuid(),
       ord_UUID uuid NOT NULL,
       pro_UUID uuid NOT NULL,
       odt_unit_price numeric(38, 2) NOT NULL,
       CONSTRAINT odt_pk PRIMARY KEY (ord_uuid, pro_uuid),
       CONSTRAINT ord_fkey FOREIGN KEY (ord_UUID) REFERENCES public.order_header(ord_UUID),
       CONSTRAINT pro_fkey FOREIGN KEY (pro_UUID) REFERENCES public.product(pro_UUID)
);

-- ---------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE VIEW public.order_info_view
AS
select oh.ord_uuid, oh.ord_code, ord_date,
       c.cli_uuid, concat(c.cli_name, ' ', c.cli_last_name) AS cli_names,
       od.odt_uuid, p.pro_uuid, p.pro_name, p.pro_code, od.odt_unit_price
from order_header oh
         inner join order_detail od ON oh.ord_uuid = od.ord_uuid
         inner join client c on c.cli_uuid = oh.cli_uuid
         inner join product p on p.pro_uuid = od.pro_uuid;



CREATE table if not exists public.order_info_tmp (
     ord_uuid uuid NOT NULL,
     ord_code varchar NOT NULL,
     ord_date varchar NOT NULL,
     cli_uuid uuid NOT NULL,
     cli_names varchar NOT NULL,
     odt_uuid uuid NOT NULL,
     pro_uuid uuid NOT NULL,
     pro_name varchar NOT NULL,
     pro_code varchar NOT NULL,
     odt_unit_price numeric(38, 2) NOT null,
     CONSTRAINT order_info_tmp_pk PRIMARY KEY (ord_uuid, pro_uuid)
);

CREATE OR REPLACE FUNCTION public.client_order_info_function()
    RETURNS trigger as '
    begin
        insert into public.order_info_tmp
        select ord_uuid, ord_code, ord_date::varchar, cli_uuid, cli_names, odt_uuid, pro_uuid, pro_name, pro_code, odt_unit_price
        from public.order_info_view
        where exists(
            select 1 from public.order_info_view where cli_uuid  = new.cli_uuid
        );
        truncate table public.order_info_tmp;
        return NEW;
    END
' LANGUAGE plpgsql;

drop trigger if exists on_client_order_info on public.client;
create TRIGGER on_client_order_info
    AFTER UPDATE OF cli_name, cli_last_name ON public.client
    FOR EACH row
EXECUTE PROCEDURE public.client_order_info_function();


-- ----------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION public.product_order_info_function()
    RETURNS trigger as '
    begin
        insert into public.order_info_tmp
        select ord_uuid, ord_code, ord_date::varchar, cli_uuid, cli_names, odt_uuid, pro_uuid, pro_name, pro_code, odt_unit_price
        from public.order_info_view
        where exists(
            select 1 from public.order_info_view where pro_uuid  = new.pro_uuid
        );
        truncate table public.order_info_tmp;
        return NEW;
    END
'  LANGUAGE plpgsql;

drop trigger if exists on_product_order_info on public.product;
create TRIGGER on_product_order_info
    AFTER UPDATE OF pro_code, pro_name ON public.product
    FOR EACH row
EXECUTE PROCEDURE public.product_order_info_function();

-- ----------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION public.order_detail_info_function()
    RETURNS trigger as '
    begin
        insert into public.order_info_tmp
        select ord_uuid, ord_code, ord_date::varchar, cli_uuid, cli_names, odt_uuid, pro_uuid, pro_name, pro_code, odt_unit_price
        from public.order_info_view
        where odt_uuid = new.odt_uuid;
        truncate table public.order_info_tmp;
        return NEW;
    end
'  LANGUAGE plpgsql;

drop trigger if exists on_order_detail_info on public.order_detail;
create TRIGGER on_order_detail_info
    AFTER INSERT ON public.order_detail
    FOR EACH row
EXECUTE PROCEDURE public.order_detail_info_function();