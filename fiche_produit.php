<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>details</title>
    <style>
        .card
        {
            min-width:320px;
            min-height:450px;
            border:1px solid black;
            border-radius:10px;
            margin-inline:10px;
            margin-block:10px;
            padding:15px;
            position:absolute;
            top:50%;
            left:50%;
            transform:translate(-50%,-50%);
        }
        i
        {
            text-align:center;
            border-bottom:1px solid black;
            padding:2px;
        }
       .ibt
       {
        margin-top:10px;
       }
        .card h3
        {
            text-align:center;
        }
        .card h4
        {
            text-align:center;
        }
        .card a
        {
            text-align:center;
            width:275px;
            display:block;
            margin:0 auto;
        }
        .card img
        {
            width:250px;
            height:250px;
            display:block;
            margin:0 auto;
        }
        .container
        {
            display:flex;
        }
    </style>
</head>
<body>
    
</body>
</html>
<?php
    $connect=mysqli_connect('localhost','root','','projetfinal');
    @$id=$_GET['id'];
    if(isset($id))
    {
        $req1="SELECT* FROM produits WHERE idproduit='$id'";
        $exec=mysqli_query($connect,$req1);
        $ligne=mysqli_num_rows($exec);
        if($ligne>0)
        {
            $row=mysqli_fetch_assoc($exec);
            echo"<div class='card'>";
                echo"<img src='{$row['picture']}'>";
                echo"<p> <strong>Description : </strong><br>{$row['description']}</p>";
                echo"<p> <strong>Color : </strong>{$row['color']}</p>";
                echo"<p> <strong>brand: </strong>{$row['brand']}</p>";
                echo"<p>  <strong>Price : </strong>{$row['price']} $</p>";
                echo"<p> <strong>categorie: : </strong>{$row['categorie']}</p>";
                if($row['stock']<0)
                {
                    echo"Le produits n'est plus diponible!!";
                }
                else
                {
                    echo"<i>Voici le nombre de produit que vous pouvez acheter: {$row['stock']}</i>";
                    echo"<form action='panier.php' method='post'>";
                        echo"<input type='hidden' name='id' value=$id>";
                       echo"<div class='ibt'>";
                            echo"<label >Choisissez la quantite de produit que vous  souhaiter commander</label>";
                            echo"<select name='quantite'>";
                                for($i=1; $i<=$row['stock'] && $i<=5 ;$i++)
                                {
                                    echo"<option>{$i}</option>";
                                }
                            echo"</select><br>";
                            echo"<input type='submit' value='ajouter au panier' name='btna'><br>";
                            echo"<a href='index.php'>Voulez vous encore faire des achats?</a>";
                       echo"</div>";
                    echo"</form>";
                }
            echo"</div>";
        }
    }
?>