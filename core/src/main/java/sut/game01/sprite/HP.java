package sut.game01.sprite;
import playn.core.Layer;
import playn.core.PlayN;
import playn.core.Pointer;
import playn.core.util.Callback;
public class HP {

    private Sprite sprite;
    private int spriteIndex = 0;
    private boolean hasLoaded = false;
    private enum State {
        idle,ball,kling
    };
    private State state = State.idle;
    private int e = 0;
    private int offset = 0;

    public HP(final float x , final float y){
        this.sprite= SpriteLoader.getSprite("images/sprite2/sprite2.json");
        sprite.addCallback(new Callback<Sprite>() {
            @Override
            public void onSuccess(Sprite result) {
                sprite.setSprite(spriteIndex);
                sprite.layer().setOrigin(sprite.width()/2f , sprite.height()/2f);
                sprite.layer().setTranslation(x,y+13f);
                hasLoaded = true;
            }

            @Override
            public void onFailure(Throwable cause) {
                PlayN.log().error("Error loading image!",cause);

            }
        });

        sprite.layer().addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event){
                state = State.idle;
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


        if (e>250){
            switch(state){
                case idle: offset = 0;
                    if(spriteIndex == 2){
                        state = State.ball;
                    }
                    break;
                case ball: offset = 4;
                    if(spriteIndex == 6){
                        state = State.kling;
                    }
                    break;
                case kling: offset = 8;

                    break;

            }
            spriteIndex  =offset + ((spriteIndex + 1)% 4);
            sprite.setSprite(spriteIndex);
            e=0;
        }

    }
}
