<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>First Steps Into PHP</title>

</head>
<body>
    <form>
        N: <input type="text" name="num" />
        <input type="submit" />
    </form>
    <?php
     if (isset($_GET['num']))
     {
         $num = intval($_GET['num']);
         for ($i = $num; $i >= 2 ; $i --) {
             $isPrime = true;
             for ($y = 2; $y < $i; $y ++) {
                 if ($i % $y == 0) {
                     $isPrime = false;
                     break;
                 }
             }
             if ($isPrime) {
                 echo "$i ";
             }
         }
     }
    ?>
</body>
</html>