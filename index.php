

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Boutique</title>
    <style>
        .card
        {
            width:320px;
            height:450px;
            border:1px solid black;
            border-radius:10px;
            margin-inline:10px;
            margin-block:10px;
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
    <div class='container'>
                <?php
                    try
                    {
                        $connect=mysqli_connect('localhost','root','','projetfinal');
                        $req="SELECT * FROM produits";
                        $exec1=mysqli_query($connect,$req);
                        $ligne=mysqli_num_rows($exec1);
                        //le mysqli_fecth_assoc doit etre en parametre de while($row1=mysqli_fetch_assoc($exec1))
                            /* $req="SELECT * FROM produits where id=1";
                            $exec1=mysqli_query($connect,$req);
                            $row= mysqli_fetch_assoc($exec1);
                            echo $row['titre'];
                            < img src='images/bleu.jpg'  >
                        */

                        while($row1=mysqli_fetch_assoc($exec1))
                        {
                                @$id=$row1['idproduit'];
                                echo "<div class='card'>";
                                echo"<h3>".$row1['titre']."</h3>";
                                echo"<a href='fiche_produit.php?id={$id}'>";
                                    echo"<img src='{$row1['picture']}'>";
                                echo"</a>";
                                
                                echo"<h4>{$row1['price']} $</h4>";
                                echo"<a href=''>detaille de l'article</a>";
                                echo "</div>";
                        }
                    }
                    catch(Execption $e)
                    {
                        echo"Erreur de chargement de la page!!";
                    }
                ?>
    </div>
</body>
</html>
