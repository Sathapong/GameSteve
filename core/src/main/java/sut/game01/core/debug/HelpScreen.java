package sut.game01.core.debug;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Layer;
import playn.core.Pointer;
import sut.game01.sprite.HP;
import sut.game01.sprite.Sprite;
import sut.game01.sprite.Zealot;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;
import tripleplay.ui.Root;

import static playn.core.PlayN.assets;
import static playn.core.PlayN.graphics;

/**
 * Created by all user on 4/3/2557.
 */
public class HelpScreen extends Screen {
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
    public HelpScreen(ScreenStack ss){
        this.ss =ss;
    }

    Image bgImage = assets().getImage("images/bghelp.png");
    ImageLayer bgLayer = graphics().createImageLayer(bgImage);

    Image backImage = assets().getImage("images/back.png");
    ImageLayer backlayer = graphics().createImageLayer(backImage);
    //Attacklayer.setTranslation(100,100);


    @Override
    public void wasAdded() {
        super.wasAdded();
        layer.add(bgLayer);
        layer.add(backlayer);
        backlayer.addListener(new Pointer.Adapter() {
            @Override
            public void onPointerEnd(Pointer.Event event) {
                ss.remove(ss.top());
            }
        });
    }
}
