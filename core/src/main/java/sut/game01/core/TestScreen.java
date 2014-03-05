package sut.game01.core;


import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.*;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.Contact;
import playn.core.*;
import playn.core.Image;
import playn.core.util.Callback;
import playn.core.util.Clock;
import react.UnitSlot;
import sut.game01.core.debug.DebugDrawBox2D;
import sut.game01.sprite.HP;
import sut.game01.sprite.Sprite;
import sut.game01.sprite.Zealot;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;

import static playn.core.PlayN.*;

import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;



import java.awt.*;
import java.awt.Button;

public class TestScreen extends Screen {


    private final ScreenStack ss;
    private World world;
    private Zealot z;

    final HP h = new HP(200f,250f);
    public static float M_PER_PIXEL = 1/26.666667f;
    private static int width = 24;
    private static int height = 18;

    private boolean showDebugDraw=true;
    private DebugDrawBox2D debugDraw;
    private Root root;
    private boolean contacted = true;
    private static int contactCheck = 0;
    private Layer Attacklayer;
    private static int att=0;
    private Body body2;
    private Sprite sprite;
    private Body bodyZealot;


    @Override
    public void wasAdded(){
        super.wasAdded();




//        Body ground2 = world.createBody(new BodyDef());
//        PolygonShape groundShape2 = new PolygonShape();
//        groundShape.setAsEdge(new Vec2(1f,height-2f),
//                new Vec2(width-20f,height-20f));
//        ground.createFixture(groundShape,0.0f);
//
//        Body ground3 = world.createBody(new BodyDef());
//        PolygonShape groundShape3 = new PolygonShape();
//        groundShape.setAsEdge(new Vec2(20f,height-2f),
//                new Vec2(width-20f,height-20f));
//        ground.createFixture(groundShape,0.0f);



        Image bgImage = assets().getImage("images/bg1.png");
        ImageLayer bgLayer = graphics().createImageLayer(bgImage);
        layer.add(bgLayer);



        bgImage.addCallback(new Callback<Image>() {
            @Override
            public void onSuccess(Image result) {
            }

            @Override
            public void onFailure(Throwable cause) {

            }
        });

        Image attackImage = assets().getImage("images/attack.png");
        ImageLayer Attacklayer = graphics().createImageLayer(attackImage);
        Attacklayer.setSize(99,99);
        Attacklayer.setTranslation(520,390);

        Image backImage = assets().getImage("images/back.png");
        ImageLayer backlayer = graphics().createImageLayer(backImage);
        //Attacklayer.setTranslation(100,100);

        layer.add(Attacklayer);
        Attacklayer.addListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {

               z.getbody().applyLinearImpulse(new Vec2(-100f,0f),z.getbody().getPosition());
            }
        });

        layer.add(backlayer);
        backlayer.addListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {
                ss.remove(ss.top());
            }
        });


//        this.layer.add(bgLayer);



        createWorld();

        z = new Zealot(world,200f,200f);
        layer.add(z.layer());
//

//        layer.add(h.layer());


    }

    public void createWorld(){
        Vec2 gravity = new Vec2(0.0f,10.0f);
        world = new World(gravity,true);
        world.setWarmStarting(true);
        world.setAutoClearForces(true);
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {

            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold manifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse contactImpulse) {

            }
        });

        if (showDebugDraw){
            CanvasImage image = graphics().createImage(
                    (int) (width / TestScreen.M_PER_PIXEL),
                    (int) (height / TestScreen.M_PER_PIXEL));
            layer.add(graphics().createImageLayer(image));
            debugDraw = new DebugDrawBox2D();
            debugDraw.setCanvas(image);
            debugDraw.setFlipY(false);
            debugDraw.setStrokeAlpha(150);
            debugDraw.setFillAlpha(75);
            debugDraw.setStrokeWidth(2.0f);
            debugDraw.setFlags(DebugDraw.e_shapeBit|DebugDraw.e_jointBit|DebugDraw.e_aabbBit);
            debugDraw.setCamera(0,0,1f/TestScreen.M_PER_PIXEL);
            world.setDebugDraw(debugDraw);
        }

        Body ground = world.createBody(new BodyDef());
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsEdge(new Vec2(3f,height-3),
                new Vec2(width-3f,height-3));
        ground.createFixture(groundShape,0.0f);

    }

    private void createBox(){
        BodyDef bf =new BodyDef();
        bf.type = BodyType.DYNAMIC;
        bf.position =new Vec2(0,0);
        Body body = world.createBody(bf);
        PolygonShape shape =new PolygonShape();
        shape.setAsBox(1f,1f);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 0.1f;
        fd.friction = 0.1f;
        fd.restitution = 1.5f;
        body.createFixture(fd);
        body.setLinearDamping(0.5f);
        body.setTransform(new Vec2(10f, 10f), 0);

    }

    private void createBox2(){
        BodyDef bf2 =new BodyDef();
        bf2.type = BodyType.DYNAMIC;
        bf2.position =new Vec2(0,0);
        body2 = world.createBody(bf2);
        PolygonShape shape =new PolygonShape();
        shape.setAsBox(1.5f,1.5f);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 0.1f;
        fd.friction = 0.1f;
        fd.restitution = 1.5f;
        body2.createFixture(fd);
        body2.setLinearDamping(0.5f);
        body2.setTransform(new Vec2(15f, 15f), 0);

    }



    public TestScreen(ScreenStack ss){
        this.ss = ss;
    }

    public void contact(Contact contact){
        contacted = true;
        contactCheck = 0;



    }

    @Override
    public void update(int delta) {
        super.update(delta);
        world.step(0.0333f,10,10);
        z.update(delta);
        h.update(delta);

    }

    @Override
    public void paint(Clock clock){
        super.paint(clock);
        if (showDebugDraw){
            debugDraw.getCanvas().clear();
            world.drawDebugData();
        }
        z.paint(clock);

    }







}