const mysql = require("mysql");
const dotenv = require("dotenv");
let instance = null;
dotenv.config();

const connection = mysql.createConnection({
  host: process.env.DB_HOST,
  user: process.env.DB_USER,
  password: process.env.DB_PASSWORD,
  database: process.env.DB_NAME,
  port: process.env.DB_PORT,
});

connection.connect((err) => {
  if (err) {
    console.error("Error connecting to the database:", err);
    return;
  }
  console.log("Database status: " + connection.state);
});

class DbService {
  static getDbServiceInstance() {
    return instance ? instance : new DbService();
  }

  async getAllSpots() {
    try {
      const response = await new Promise((resolve, reject) => {
        const query = "SELECT * FROM spots";

        connection.query(query, (err, results) => {
          if (err) reject(new Error(err.message));
          resolve(results);
        });
      });
      console.log(response);
      return response;
    } catch (error) {
      console.log(error);
    }
  }

  async insertSpot({ name, building, floor, seats, imgURL }) {
    try {
      const response = await new Promise((resolve, reject) => {
        const query = `INSERT INTO spots (name, building, floor, seats, imgURL) VALUES (?, ?, ?, ?, ?)`;
        const params = [
          name || null,
          building || null,
          floor || null,
          seats || 0,
          imgURL || null,
        ];

        connection.query(query, params, (err, result) => {
          if (err) return reject(new Error(err.message));
          resolve({ insertId: result.insertId });
        });
      });
      return response;
    } catch (error) {
      console.log(error);
      throw error;
    }
  }
}

module.exports = DbService;
