<%--
  Created by IntelliJ IDEA.
  User: devnull
  Date: 21/10/18
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>The Knock-out Song</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<div id="btn">
    <div class="row">
        <button type="button" class="btn btnsharp btnoutline btnoutlinecolor">PLAY</button>
    </div>
    <div class="row">
        <button type="button" class="btn btnsharp btnoutline btnoutlinecolor">UPLOAD</button>
    </div>
</div>
<script type="text/javascript">
    var images = ['1.jpg', '2.jpg', '3.jpg', '4.jpg', '5.jpg', '6.jpg', '7.jpg', '8.jpg', '9.jpg', '10.png', '11.png', '12.png', '13.png', '14.png', '15.png', '16.png'];
    $('#banner-load').css('background-image', images[Math.floor(Math.random() * images.length)])
    var width = parseInt(window.innerWidth),
        height = parseInt(window.innerHeight);
    var btnW, btnH;
    btnW = 200;
    btnH = 120;
    document.getElementById("btn").style.marginTop = "" + (height - btnH) / 2 + "px";
    document.getElementById("btn").style.marginLeft = "" + (width - btnW) / 2 + "px";
</script>
</body>
</html>
