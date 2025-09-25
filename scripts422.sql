CREATE TABLE persons (
    id          BIGINT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    age         INT CHECK (age > 0),
    has_license BOOLEAN DEFAULT false
);

CREATE TABLE cars (
    id    BIGINT PRIMARY KEY,
    brand VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    price NUMERIC(19, 2) CHECK (price > 0)
);

CREATE TABLE person_car (
    person_id BIGINT REFERENCES persons(id),
    car_id    BIGINT REFERENCES cars(id),
    PRIMARY KEY (person_id, car_id)
);