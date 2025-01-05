const express = require('express');
const bodyParser = require('body-parser');
const mongoose = require('mongoose');
const ProduitModel=require("./produitmodel")

const router = require('./router')

const app = express();
app.use(bodyParser.json());

// Configuration du port
const PORT = 3024;
app.use(router)

// Connexion à MongoDB
mongoose
    .connect('mongodb://localhost:27017/teccart')
    .then(() => console.log('Connexion avec MongoDB effectuée avec succès'))
    .catch((err) => console.error('Connexion avec MongoDB échouée'));

// Définition du modèle Produit

// Routes
// GET /produits : Récupère tous les produit

// Lancement du serveur
app.listen(PORT, () => {
    console.log(`Le serveur écoute sur le port ${PORT}`);
});
