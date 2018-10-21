function loadBackground(forElement) {
    let filename = `pictures/bg/bg${Math.round(Math.random() * 16)}.jpg`;
    forElement.css('background-image', `url("${filename}")`);
    return filename;
}

function adaptCanvas(canvasObject) {
    canvasObject.width = $(window).width();
    canvasObject.height = $(window).height();
}


$(function () {
    let bgImage = $("#bgimage");
    let filename = loadBackground(bgImage);
    let loader = $("#load");
    let gf = $("#gameField")[0];

    let img = new Image();
    img.src = filename;

    img.onload = () => new GameEngine(gf, img);

    loader.fadeTo(2000, 0.1);
});