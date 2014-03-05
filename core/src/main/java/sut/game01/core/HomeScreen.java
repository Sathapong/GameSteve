package sut.game01.core;

import playn.core.Font;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.Pointer;
import react.UnitSlot;
import sut.game01.core.debug.HelpScreen;
import sut.game01.core.debug.MoreScreen;
import sut.game01.core.debug.ScoreScreen;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;
import tripleplay.game.Screen;
import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;



import static playn.core.PlayN.*;

/**
 * Created by BkFamily on 21/1/2557.
 */
public class HomeScreen extends UIScreen {
    public static final Font TITLE_FONT = graphics().createFont(
            "Helvetiva",
            Font.Style.PLAIN,
            24 );

    private final  ScreenStack  ss;
    private Root root;
    public HomeScreen(ScreenStack ss){
        this.ss =ss;
    }

    @Override
    public void wasShown() {
        super.wasShown();
        Image bgImage = assets().getImage("images/bggame.png");
        ImageLayer bgLayer = graphics().createImageLayer(bgImage);

        Image nameImage = assets().getImage("images/namegame.png");
        ImageLayer nameLayer = graphics().createImageLayer(nameImage);
        nameLayer.setSize(400,200);
        nameLayer.setTranslation(100,10);

        Image playImage = assets().getImage("images/play.png");
        ImageLayer playLayer = graphics().createImageLayer(playImage);
        playLayer.setTranslation(450,80);

        Image scoreImage = assets().getImage("images/score.png");
        ImageLayer scoreLayer = graphics().createImageLayer(scoreImage);
        scoreLayer.setTranslation(450,150);

        Image moreImage = assets().getImage("images/more.png");
        ImageLayer moreLayer = graphics().createImageLayer(moreImage);
        moreLayer.setTranslation(450,220);

        Image helpImage = assets().getImage("images/help.png");
        ImageLayer helpLayer = graphics().createImageLayer(helpImage);
        helpLayer.setTranslation(450,290);

        layer.add(bgLayer);
        layer.add(nameLayer);
        layer.add(playLayer);
        playLayer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                ss.push(new TestScreen(ss));
            }
        });
        layer.add(scoreLayer);
        scoreLayer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                ss.push(new ScoreScreen(ss));
            }
        });
        layer.add(moreLayer);
        moreLayer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                ss.push(new MoreScreen(ss));
            }
        });
        layer.add(helpLayer);
        helpLayer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                ss.push(new HelpScreen(ss));
            }
        });











//        root =  iface.createRoot(
//                AxisLayout.vertical().gap(15),
//                SimpleStyles.newSheet(),layer);
//        root.addStyles(Style.BACKGROUND.is(Background.
//                bordered(0xFFCCCCCC, 0xFF99CCFF, 5).
//                inset(5, 10)));
//        root.setSize(width(),height());
//        root.add(new Label("WelCome To...NINJA OSEKAI")
//                .addStyles(Style.FONT.is(HomeScreen.TITLE_FONT)));
//        root.add(new Button("Start").onClick(new UnitSlot(){
//            public void onEmit(){
//                ss.push(new TestScreen(ss));
//            }
//        }));

    }
}