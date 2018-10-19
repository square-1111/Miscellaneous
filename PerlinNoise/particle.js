function Particle(){
    this.pos = createVector(random(w),random(h));
    this.vel = createVector(0, 0);
    this.acc = createVector(0, 0);
    this.maxspeed = 4;

    this.prevPos = this.pos.copy();



    this.update = function(){
        this.vel.add(this.acc);
        this.vel.limit(this.maxspeed);
        this.pos.add(this.vel);
        this.acc.mult(0);
    }

    this.follow = function(vectors){
        var x = floor(this.pos.x/scl);
        var y = floor(this.pos.y/scl);
        var index = x + y*cols;
        var force = vectors[index];
        this.applyForce(force);
    }

    this.applyForce = function(force){
        this.acc.add(force);
    }

    this.show = function(){
        stroke(0,50);
        fill(255,0,0,255);
        strokeWeight(2);
        line(this.pos.x, this.pos.y,this.prevPos.x,this.prevPos.y);
        this.updatePrev();
    }

    this.updatePrev = function(){
        this.prevPos.x = this.pos.x;
        this.prevPos.y = this.pos.y;
    }
    this.edges = function(){
        if(this.pos.x > w){
            this.pos.x = 0;
            this.updatePrev();
        }
        if(this.pos.x < 0){
            this.pos.x = w;
            this.updatePrev();
        }
        if(this.pos.y > h){
            this.pos.y = 0;
            this.updatePrev();
        }
        if(this.pos.y < 0){
            this.pos.y = h;
            this.updatePrev();
        }
    }
}