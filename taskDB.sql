CREATE TABLE tasks (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    priority VARCHAR(20),
    status VARCHAR(20) DEFAULT 'To Do'
);
