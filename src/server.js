import express from "express";
import path from "path";
import { fileURLToPath } from "url";

const app = express();

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const staticPublic = path.join(__dirname, "../public");

// Parse incoming JSON bodies
app.use(express.json());

app.use(express.static(staticPublic));

// Use index.html as our default page
app.get("/", (req, res) => {
  res.sendFile(path.join(staticPublic, "index.html"));
});

// Endpoint to receive JSON at /newSpot, right now it doesn't store it anywhere but eventually it'll be sent to our MySQL database
app.post("/newSpot", (req, res) => {
  console.log("POST /newSpot body:", req.body);
  res.json({ status: "ok", received: req.body });
});

//app.use("/routes/databaseRoute.js");

// Print whenever we start the server
app.listen(3000, () =>
  console.log("Server listening on http://localhost:3000")
);
