<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>{{song_title}}</title>
    <link rel="stylesheet" href="css/styles.css">
    <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
    <script src="js/background_loader.js"></script>
    <script src="js/game.js"></script>
</head>
<body>
    <div id="load"></div>
    <div id="bgimage"></div>
    <canvas id="gameField" onresize="adaptCanvas(document.getElementById('gameField'))"></canvas>
        <script>
            let doc = document.getElementById("gameField");
            adaptCanvas(doc);
        </script>

</body>
</html>
