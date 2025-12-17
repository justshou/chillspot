import express from "express";

// Execute SQL statements from strings
db.exec(`
CREATE TABLE spots (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT,
    building TEXT,
    floor TEXT,
    seats INTEGER,
    imgURL TEXT
);
`);

export default router;
