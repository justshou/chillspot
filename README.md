# ChillSpot

ChillSpot is a University Application used for finding spots to chill on campus.

## Prerequisites

- **Node.js** (v14 or later) and npm (Node Package Manager)
- **MySQL** (Ensure that you have MySQL installed and running)

## Installation & Setup

Clone the repository

```bash
git clone https://github.com/justshou/chillspot.git
```

Inside the newly cloned directory, run the following to install dependencies

```bash
npm install
```

Next, download and install [XAMPP](https://www.apachefriends.org/) to set up a MySQL Database

After installation, run XAMPP and start Apache and MySQL.

Click "Admin" for MySQL, and run the following under "SQL" at the top

```sql
CREATE DATABASE spots_db;

USE spots_db;

CREATE TABLE spots (
id INTEGER PRIMARY KEY AUTO_INCREMENT,
name TEXT,
building TEXT,
floor TEXT,
seats INTEGER,
imgURL TEXT
);
```

Finally, rename `dotenv` to `.env`

## Usage

To start the frontend application, run the following

```bash
npm run dev
```

To start the executable application, run the .exe provided

## Development Team

Anthony Mashou\
Hector Najar\
Vincent Nguyen
