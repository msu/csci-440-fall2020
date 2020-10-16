
EXPLAIN QUERY PLAN
SELECT *
FROM employees
WHERE Email = 'andrew@chinookcorp.com';

EXPLAIN QUERY PLAN
SELECT *
FROM employees
WHERE Email = 'andrew%';

EXPLAIN QUERY PLAN
SELECT *
FROM employees
WHERE Email = '%andrew%';


-- email index
CREATE UNIQUE INDEX idx_employees_email
    ON employees (Email);

SELECT *
FROM playlist_track
WHERE PlaylistId = 1 AND TrackId = 1;

CREATE INDEX idx_playlist_track_1
    ON playlist_track (PlaylistId);

CREATE INDEX idx_playlist_track_2
    ON playlist_track (PlaylistId, TrackId);


CREATE INDEX idx_invoice_line_amount
    ON invoice_items(UnitPrice * Quantity);

SELECT InvoiceLineId,
       InvoiceId,
       UnitPrice * Quantity
FROM invoice_items
WHERE Quantity * Unitprice > 10;

CREATE TRIGGER validate_email_before_insert_employees
    BEFORE INSERT ON employees
    WHEN NEW.email NOT LIKE '%@%.%'
BEGIN
    RAISE (ABORT,'Invalid email address');
END;

CREATE TRIGGER denormalize_invoice_line_total
    AFTER UPDATE
    ON invoice_items
    BEGIN
        UPDATE invoice_items
        SET TotalAmount = new.UnitPrice * new.Quantity
        WHERE InvoiceLineId = new.InvoiceLineId;
    END;
