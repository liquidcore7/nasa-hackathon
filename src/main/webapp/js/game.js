class Point {
    constructor(x, y) {
        this.x = x;
        this.y = y;
    }
}

class Particle {
    draw(canvas) {
        canvas.drawImage(this.sprite, this.x, this.y, this.sprite.width * this.scale, this.sprite.height * this.scale);
    }

    constructor(sprite, x, y, scaleFactor) {
        this.scale = scaleFactor;
        this.sprite = new Image();
        this.sprite.src = sprite;
        this.x = x;
        this.y = y;
        this.sprite.onload = () => {this.x -= this.sprite.width * scaleFactor / 2; this.y -= this.sprite.height * scaleFactor/ 2;};
    }
}

const viewAngle = Math.PI / 6;

class Planet {

    constructor(initPoint, sprite, orbitRadius, angle, period, canvas, scaleFactor) {
        this.canvas = canvas;

        this.increment = 2 * Math.PI / (period); // assuming 1s updates
        this.initX = initPoint.x;
        this.initY = initPoint.y;
        this.orbitRadius = orbitRadius;
        this.angle = angle;
        this.particle = new Particle(sprite, 0, 0, scaleFactor);
    }

    updPoint(timePassed) {

        this.angle += this.increment * timePassed / 1000.0;
        if (this.angle >= 2 * Math.PI)
            this.angle -= 2 * Math.PI;

        this.particle.x = this.initX + this.orbitRadius * Math.cos(this.angle);
        this.particle.y = this.initY + this.orbitRadius * Math.sin(this.angle) * Math.sin(viewAngle);

        this.particle.draw(this.canvas);
    }
}

class GameEngine {
    advance() {
        var start = performance.now();
        let self = this;
        requestAnimationFrame(function upd(time) {

            self.drawer.fillRect(0,0, self.canvas.width, self.canvas.height);
            self.sun.draw(self.drawer);

            let passed = time - start;
            start = time;
            self.planets.forEach(p => p.updPoint(passed));

            requestAnimationFrame(upd);
        });
    }

    destroyPlanet(i) {
        this.planets[i] = null;
    }

    spawnPlanets() {
        let middle = new Point(this.canvas.width / 2, this.canvas.height / 2);
        this.sun = new Particle('pictures/planets/sun.png', middle.x, middle.y, 0.4);
        this.sun.y += this.sun.sprite.height / 2 * Math.sin(viewAngle);
        this.sun.draw(this.drawer);

        for (var i = 0; i < 8; ++i) {
            this.planets.push(
                new Planet(middle, this.prefix + i + '.png', 0.11 * (i + 1) * middle.x, i * Math.PI / 5.0, (i + 1) * 1.3, this.drawer, 0.4)
            );
        }
    }

    constructor(canvas, fillStyle) {
        this.prefix = 'pictures/planets/';
        this.canvas = canvas;
        this.drawer = canvas.getContext('2d');
        this.drawer.fillStyle = this.drawer.createPattern(fillStyle, 'repeat');
        this.drawer.fillRect(0,0,this.canvas.width, this.canvas.height);
        this.sun = null;
        this.planets = [];
        this.spawnPlanets();
        this.advance();
    }
}



