CREATE TABLE IF NOT EXISTS item_groups (
    id INT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    group_name VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS stocks (
    id INT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    stock_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS operation_types (
    id INT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    type_name VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS employees (
    id INT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    username VARCHAR(30) NOT NULL,
    user_password VARCHAR(30) NOT NULL,
    email VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS doc_types (
    id INT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    type_name VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS calc_types (
    id INT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    type_name VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS customers (
    id INT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    customer_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS vendors (
    id INT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    vendor_name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS items (
    id INT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    purchase_price NUMERIC(12, 2),
    sale_price NUMERIC(12, 2),
    stock_quantity INT NOT NULL,
    article VARCHAR(30) NOT NULL,
    item_name VARCHAR(255) NOT NULL,
    barcode VARCHAR(14),
    item_group_id INT NOT NULL,
    stock_id INT NOT NULL,
    FOREIGN KEY (item_group_id) REFERENCES item_groups(id),
    FOREIGN KEY (stock_id) REFERENCES stocks(id)
);

CREATE TABLE IF NOT EXISTS arrivals (
    id INT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    doc_num INT,
    total_amount NUMERIC(12, 2),
    oper_type_id INT NOT NULL,
    date_arrival TIMESTAMP WITH TIME ZONE,
    stock_id INT NOT NULL,
    employee_id INT NOT NULL,
    vendor_id INT NOT NULL,
    FOREIGN KEY (oper_type_id) REFERENCES operation_types(id),
    FOREIGN KEY (stock_id) REFERENCES stocks(id),
    FOREIGN KEY (employee_id) REFERENCES employees(id),
    FOREIGN KEY (vendor_id) REFERENCES vendors(id),
    UNIQUE(doc_num)
);

CREATE TABLE IF NOT EXISTS sales (
    id INT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    doc_num INT NOT NULL,
    price NUMERIC(12, 2) NOT NULL,
    total_amount NUMERIC(12, 2) NOT NULL,
    quantity INT NOT NULL,
    article VARCHAR(30),
    doc_type_id INT NOT NULL,
    customer_id INT NOT NULL,
    type_calc_id INT NOT NULL,
    employee_id INT NOT NULL,
    FOREIGN KEY (doc_type_id) REFERENCES doc_types(id),
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (type_calc_id) REFERENCES calc_types(id),
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);

CREATE TABLE IF NOT EXISTS items_from_arrival (
    id INT PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    item_name VARCHAR(255) NOT NULL,
    article VARCHAR(30) NOT NULL,
    price NUMERIC(12, 2) NOT NULL,
    quantity INT NOT NULL,
    amount NUMERIC(12, 2) NOT NULL,
    arrival_id INT NOT NULL,
    FOREIGN KEY (arrival_id) REFERENCES arrivals(id)
);