var h = 600;
var w = 600;

var scl = 10;
var start = 100;
var inc = 0.05;
var zoff = 0;

var rows,cols;
var fr;

var particles = [];

var flowField;


function setup(){
    createCanvas(w, h);
    // fill(255,0,0,255);
    rows = floor(w/scl);
    cols = floor(h/scl);

    flowField = new Array(cols*rows);

    for(var i=0;i<100;i++){
        particles[i] = new Particle();
    }
    background(0);
}

function draw(){
    frameRate(20);
    var yoff = 0;

    for(var y=0;y<cols;y++){
        var xoff=0;
        for(var x=0;x<rows;x++){
            
            var index = x + y*cols;
            var angle = noise(xoff,yoff,zoff)*TWO_PI*5;
            var vec = p5.Vector.fromAngle(angle);
            vec.setMag(1);
            flowField[index] = vec; 

            // stroke(0,20);
            // strokeWeight(1);
            // push();
            // translate(x*scl,y*scl);
            // rotate(vec.heading());
            // line(0,0,scl,0);

            xoff += inc;
            // pop();
            
        }
        yoff += inc;

        zoff += inc/50;
    }
    for(var i=0;i<particles.length;i++){
        particles[i].follow(flowField);
        particles[i].update();
        particles[i].show();
        particles[i].edges();

    }
}