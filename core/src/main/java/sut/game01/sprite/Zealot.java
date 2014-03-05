package sut.game01.sprite;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import playn.core.Layer;
import playn.core.PlayN;
import playn.core.Pointer;
import playn.core.util.Callback;
import playn.core.util.Clock;
import sut.game01.core.TestScreen;

public class Zealot {

    private Sprite sprite;
    private int spriteIndex = 0;
    private boolean hasLoaded = false;
    private enum State {
        idle,ball,die
    };
    private State state = State.idle;
    private int e = 0;
    private int hp=100 , time;
    private int offset = 0;
    private Body bodyZealot;


    public Zealot(World world , final float x , final float y){
        this.sprite= SpriteLoader.getSprite("images/sprite/sprite.json");
            sprite.addCallback(new Callback<Sprite>() {
                @Override
                public void onSuccess(Sprite result) {
                    sprite.setSprite(spriteIndex);
                    sprite.layer().setOrigin((sprite.width())/2f , (sprite.height()+30)/2f);
                    sprite.layer().setTranslation(x,y+13f);


                    hasLoaded = true;
                }

            @Override
            public void onFailure(Throwable cause) {
                PlayN.log().error("Error loading image!",cause);

            }
        });

        bodyZealot= initPhysicsBody(world,TestScreen.M_PER_PIXEL*x,TestScreen.M_PER_PIXEL*y);

        sprite.layer().addListener(new Pointer.Adapter(){
            @Override
                    public void onPointerEnd(Pointer.Event event){
                state = State.ball;
                spriteIndex=-1;
                e=0;
            }
        });

    }

    public Layer layer() {
        return sprite.layer();
    }

    public void update(int delta) {
        if (!hasLoaded) return;
        e +=delta;
        time +=delta;

        if (time>=1000){
            hp=50;
        }
        if (time>=2000){
            hp=0;
        }


        if (e>250){
            switch(state){
                case idle: offset = 0;
                    if(hp==50){
                        state = State.ball;
                    }
                    break;
                case ball: offset = 4;
                    if(hp==0){
                        state = State.ball;
                    }
                    break;
                case die: offset = 8;

                    break;

            }
//            if(hp==0){
//                sprite.setSprite(11);
//            }
           // else{
            spriteIndex  =offset + ((spriteIndex + 1)% 4);
            sprite.setSprite(spriteIndex);
            //}
            e=0;
        }




    }
//
//    private Body initPhysicsBody(World world,float x,float y){
//        BodyDef bodyDef = new BodyDef();
//        bodyDef.type = BodyDef();
//        bodyDef.position




    private Body initPhysicsBody(World world,float x,float y){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position = new Vec2(0,0);
        Body body = world.createBody(bodyDef);

        ///EdgeShape shape = new EdgeShape();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(56* TestScreen.M_PER_PIXEL/2,
                ((sprite.layer().height())*TestScreen.M_PER_PIXEL/2)+0.6f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.4f;
        fixtureDef.friction = 0.1f;
        fixtureDef.restitution = 0.35f;
        body.createFixture(fixtureDef);

        body.setLinearDamping(0.2f);
        body.setTransform(new Vec2(x,y),0f);
        return body;
    }

    public void paint(Clock clock){
        sprite.layer().setTranslation((bodyZealot.getPosition().x/TestScreen.M_PER_PIXEL),
                (bodyZealot.getPosition().y/TestScreen.M_PER_PIXEL));

    }

    public Body getbody(){
        return this.bodyZealot;
    }
    }





