import express from "express";
import cors from "cors";
import dotenv from "dotenv";
import path from "path";
import { fileURLToPath } from "url";
import dbService from "./dbService.js";

const app = express();
dotenv.config();

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const staticPublic = path.join(__dirname, "../public");

// Parse incoming JSON bodies
app.use(cors());
app.use(express.json());
app.use(express.urlencoded({ extended: false }));

app.use(express.static(staticPublic));

// Use index.html as our default page
app.get("/", (req, res) => {
  res.sendFile(path.join(staticPublic, "index.html"));
});

// Endpoint to receive JSON at /newSpot, right now it doesn't store it anywhere but eventually it'll be sent to our MySQL database
// CREATE
app.post("/insert", (req, res) => {
  console.log("POST /insert body:", req.body);
  res.json({ status: "ok", received: req.body });
});

// READ
//app.use("/routes/databaseRoute.js");
app.get("/getSpots", (req, res) => {
  res.json({});
});

// Print whenever we start the server
app.listen(process.env.PORT, () =>
  console.log(`Server listening on http://localhost:${process.env.PORT}`)
);
