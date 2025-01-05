
const express = require('express');
const ProduitModel=require("./produitmodel")
const router=express.Router()

router.get('/produits', async (req, res) => {
    try {
        const produits = await ProduitModel.find();
        res.status(200).send(produits);
    } catch (err) {
        res.status(500).send('Erreur serveur.');
    }
});

// GET /produits/:id : Récupère un produit par son ID
router.get('/produits/:id', async (req, res) => {
    try {
        const produit = await ProduitModel.findById(req.params.id);
        if (!produit) {
            res.status(404).send("Produit non trouvé.");
        }
        res.status(200).send(produit);
    } catch (err) {
        res.status(500).send("Erreur serveur.");
    }
});

// POST /produit : Ajoute un nouveau produit
router.post('/produits', async (req, res) => {
    
    const nom = req.body.nom
    const prix = req.body.prix
    const quantite = req.body.quantite

    if (!nom || !prix || !quantite) {
        res.status(400).send("Données incomplètes.");
    }

    try {
        const nouveauProduit = new ProduitModel(req.body);
        await nouveauProduit.save();
        res.status(201).send(nouveauProduit);
    } catch (err) {
        res.status(500).send("Erreur serveur.");
    }
});

// PUT /produits/:id : Modifie un produit existant
router.put('/produits/:id', async (req, res) => {
    try {
        
        const nom = req.body.nom
        const prix = req.body.prix
        const quantite = req.body.quantite

        if (!nom && !prix && !quantite) {
            res.status(400).send("Données incomplètes.");
        }

        const produit = await ProduitModel.findByIdAndUpdate(req.params.id, req.body);
        
        if (!produit) {
            return res.status(404).send("Produit non trouvé.");
        }
        
        
        res.status(200).send(produit);
    } catch (err) {
        res.status(500).send("Erreur serveur.");
    }
});

// DELETE /produits : Supprime tous les produits
router.delete('/produits', async (req, res) => {
    try {
        await ProduitModel.deleteMany();
        res.status(204).send();
    } catch (err) {
        res.status(500).send("Erreur serveur.");
    }
});

// DELETE /produits/:id : Supprime un produit par son ID
router.delete('/produits/:id', async (req, res) => {
    try {
        const produit = await ProduitModel.findByIdAndDelete(req.params.id);
        if (!produit) {
            return res.status(404).send("Produit non trouvé.");
        }
        res.status(204).send();
    } catch (err) {
        res.status(500).send("Erreur serveur.");
    }
});

module.exports=router
