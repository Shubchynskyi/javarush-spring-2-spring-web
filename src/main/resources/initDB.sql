create table if not exists game.order (
                            id bigserial not null,
                            address varchar(255),
                            users_id bigint,
                            primary key (id)
);
create table if not exists game.product (
                              id bigserial not null,
                              description varchar(255),
                              title varchar(255),
                              order_id bigint,
                              primary key (id)
);
create table if not exists game.users (
                            id bigserial not null,
                            name varchar(255),
                            password varchar(255),
                            primary key (id)
);
create table game.order_product (
                                    order_id bigint not null,
                                    product_id bigint not null
);




INSERT INTO game.users (name, password) VALUES ('Admin', '123');
INSERT INTO game.users (name, password) VALUES ('User', '456');
INSERT INTO game.users (name, password) VALUES ('Guest', '789');
INSERT INTO game.users (name, password) VALUES ('Moderator', '159');

INSERT INTO game.product (description, title) VALUES ('Красные кроссовки Nike airmax', 'Кроссовки');
INSERT INTO game.product (description, title) VALUES ('Черные лосины', 'Лосины');
INSERT INTO game.product (description, title) VALUES ('Серый рюкзак', 'Рюкзак');
