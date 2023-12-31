-- liquibase formatted sql

-- changeset sokol:1
CREATE TABLE avatar (
    id BIGINT NOT NULL generated by default as identity PRIMARY KEY,
    data oid,
    file_path VARCHAR(255),
    file_size BIGINT NOT NULL,
    media_type VARCHAR(255),
    student_id BIGINT
);
-- changeset sokol:2
ALTER TABLE avatar ADD FOREIGN KEY (student_id) REFERENCES student;