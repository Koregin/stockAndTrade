delete from items;
DELETE FROM items_from_arrival;
DELETE FROM items_from_sale;
DELETE FROM arrivals;
DELETE FROM item_groups;
DELETE FROM stocks;
DELETE FROM operation_types;
DELETE FROM doc_types;
DELETE FROM calc_types;
DELETE FROM employees;
DELETE FROM vendors;



INSERT INTO item_groups(group_name) VALUES('Аудио/Видео');
INSERT INTO item_groups(group_name) VALUES('Автотехника');
INSERT INTO item_groups(group_name) VALUES('Фототехника');
INSERT INTO item_groups(group_name) VALUES('Холодильное оборудование');

INSERT INTO stocks(stock_name) VALUES('Центральный');
INSERT INTO stocks(stock_name) VALUES('Склад магазина №1');

--INSERT INTO operation_types(type_name) VALUES('Ввод остатков');
--INSERT INTO operation_types(type_name) VALUES('Приход');
--INSERT INTO operation_types(type_name) VALUES('Возврат');
--
--INSERT INTO doc_types(type_name) VALUES('Кассовый чек');
--INSERT INTO doc_types(type_name) VALUES('Товарный чек');
--
--INSERT INTO calc_types(type_name) VALUES('Наличный');
--INSERT INTO calc_types(type_name) VALUES('Безналичный');

INSERT INTO employees(username, user_password, email) VALUES('manager', 'manager', 'manager@gmail.com');

INSERT INTO customers(customer_name) VALUES('Покупатель 1');

insert into vendors(vendor_name) values ('DNS');