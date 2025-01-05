<?php
    session_start();
    require("function.php");
    $connect=mysqli_connect('localhost','root','','projetfinal');  
    @$id=$_POST['id'];
    @$stock=$_POST['stock'];
    if(isset($_POST['btna']))
    {  
        $req="SELECT * FROM produits WHERE idproduit={$id}";
        $exec=mysqli_query($connect,$req);
        $row=mysqli_fetch_assoc($exec);
        ajouterProduitDansPanier($row['titre'],$row['idproduit'],$row['stock'],$row['price']);

    }    //var_dump($_SESSION['panier']);
        echo "<table border='1' style='border-collapse: collapse' cellpadding='7'>";
   echo "<tr><td colspan='5'>Panier</td></tr>";
   echo "<tr><th>Titre</th><th>Produit</th><th>stock</th><th>Price</th><th>Action</th></tr>";
   if(empty($_SESSION['panier']['idproduit'])) // panier vide
   {
       echo "<tr><td colspan='5'>Votre panier est vide</td></tr>";
   }
   else
   {
       for($i = 0; $i < count($_SESSION['panier']['idproduit']); $i++) 
       {
           echo "<tr>";
           echo "<td>" . $_SESSION['panier']['titre'][$i] . "</td>";
           echo "<td>" . $_SESSION['panier']['idproduit'][$i] . "</td>";
           echo "<td>" . $_SESSION['panier']['stock'][$i] . "</td>";
           echo "<td>" . $_SESSION['panier']['price'][$i] . "</td>";
           $idp=$_SESSION['panier']['idproduit'][$i];
           echo "<td><a href='suspression.php?idproduit_supprimer={$idp}'><input type='button' value='supprimer'></a></td>"; 
           echo "<td><a href='modifie.php?idproduit_supprimer={$idp}'><input type='button' value='modifier'></a></td>"; 
           
           //echo"<td><form action='panier.php' method='post'><input type='submit' value='supprimer' name='sup'></form></td>";
           echo "</tr>";
       }
       echo "<tr><th colspan='3'>Total</th><td colspan='2'>" . montantTotal() . " CAD</td></tr>";
       if(isset($_GET['idproduit_supprimer']))
       {
            retirerproduitDuPanier($idp);
            header("Location:fiche_produit.php");
       }
       if(true) 
       {
           echo '<form method="post" action="">';
           echo '<tr><td colspan="5"><input type="submit" name="payer" value="Valider et déclarer le paiement" /></td></tr>';
           echo '</form>';	
       }
       else 
       {
           echo '<tr><td colspan="3">Veuillez vous <a href="inscription.php">inscrire</a> ou vous <a href="connexion.php">connecter</a> afin de pouvoir payer</td></tr>';
       }
       echo "<tr><td colspan='5'><a href='?action=vider'>Vider mon panier</a></td></tr>";
   }
   echo "</table><br />";
   echo "<i>Réglement par CHÈQUE uniquement à l'adresse suivante : 2178 rue rosemont</i><br />";
   
    
    
   

   
?>