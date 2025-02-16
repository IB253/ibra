<?php
function executeRequete($req)
{
	global $mysqli; 
	$resultat = $mysqli->query($req); 
	if (!$resultat)
	{
		die("Erreur sur la requete sql.<br />Message : " . $mysqli->error . "<br />Code: " . $req);
	}
	return $resultat;
}
//------------------------------------
function debug($var, $mode = 1) 
{
		echo '<div style="background: orange; padding: 5px; float: right; clear: both; ">';
		$trace = debug_backtrace(); 
		$trace = array_shift($trace);
		echo "Debug demand� dans le fichier : $trace[file] � la ligne $trace[line].<hr />";
		if($mode === 1)
		{
			echo "<pre>"; print_r($var); echo "</pre>";
		}
		else
		{
			echo "<pre>"; var_dump($var); echo "</pre>";
		}
	echo '</div>';
}
//------------------------------------
function internauteEstConnecte()
{  
	if(!isset($_SESSION['membre'])) 
	{
		return false;
	}
	else
	{
		return true;
	}
}
//------------------------------------
function internauteEstConnecteEtEstAdmin()
{ 
	if(internauteEstConnecte() && $_SESSION['membre']['statut'] == 1) 
	{
			return true;
	}
	return false;
}

function creationDuPanier()
{
   if (!isset($_SESSION['panier']))
   {
      $_SESSION['panier']=array();
      $_SESSION['panier']['titre'] = array();
      $_SESSION['panier']['idproduit'] = array();
      $_SESSION['panier']['stock'] = array();
      $_SESSION['panier']['price'] = array();
   }
}

function ajouterProduitDansPanier($titre,$idproduit,$stock,$price)
{
	creationDuPanier(); 
    $position_produit = array_search($idproduit,  $_SESSION['panier']['idproduit']); 
    if ($position_produit !== false)
    {
         $_SESSION['panier']['stock'][$position_produit] += $stock;
    }
    else 
    {
        $_SESSION['panier']['titre'][] = $titre;
        $_SESSION['panier']['idproduit'][] = $idproduit;
        $_SESSION['panier']['stock'][] = $stock;
		$_SESSION['panier']['price'][] = $price;
    }
}
//------------------------------------
function montantTotal()
{
   $total=0;
   for($i = 0; $i < count($_SESSION['panier']['idproduit']); $i++) 
   {
      $total += $_SESSION['panier']['stock'][$i] * $_SESSION['panier']['price'][$i];
   }
   return round($total,2);
}
//------------------------------------
function retirerproduitDuPanier($id_produit_a_supprimer)
{
	$position_produit = array_search($id_produit_a_supprimer,  $_SESSION['panier']['idproduit']);
	if ($position_produit !== false)
    {
		array_splice($_SESSION['panier']['titre'], $position_produit, 1);
		array_splice($_SESSION['panier']['idproduit'], $position_produit, 1);
		array_splice($_SESSION['panier']['stock'], $position_produit, 1);
		array_splice($_SESSION['panier']['price'], $position_produit, 1);
	}
}













