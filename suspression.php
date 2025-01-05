<?php
    session_start();
    require("function.php");
    $idp=$_GET['idproduit_supprimer'];
    if(isset($_GET['idproduit_supprimer']))
    {
        retirerproduitDuPanier($idp);
            header("Location:panier.php");
            exit();
    }
?>


