INSERT INTO guest (name) VALUES
('Joseph'),
('Antony'),
('Carl'),
('Mary'),
('Michael'),
('Amy');


INSERT INTO owner (name) VALUES
('Robert'),
('Angela'),
('Dave');


INSERT INTO property (id_owner, name) VALUES
(1, 'House'),
(2, 'Flat'),
(3, 'Flat'),
(1, 'Mansion');

INSERT INTO property_blocks (id_property, start_date, end_date, notes) VALUES
(1, '2023-11-01', '2023-11-10', 'Repaint house'),
(2, '2023-01-01', '2023-01-10', 'Repair floor'),
(3, '2023-01-01', '2023-01-10', 'Repair front window'),
(1, '2023-01-01', '2023-01-10', 'Staying there for the weekend');