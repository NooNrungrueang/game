package sut.game01.core.Screen;

import Sprite.Sprite;
import Sprite.Zealot;
import org.jbox2d.dynamics.World;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.PlayN;
import playn.core.Pointer;
import react.UnitSlot;
import tripleplay.game.ScreenStack;
import playn.core.util.Callback;
import tripleplay.game.UIScreen;
import tripleplay.ui.*;

import static tripleplay.ui.SimpleStyles.newSheet;
import static tripleplay.ui.layout.AxisLayout.vertical;

/**
 * Created by N.Rungrueang on 1/21/14.
 */
public class TestScreen extends UIScreen {
    private final ScreenStack ss;
    public TestScreen(ScreenStack ss){
            this.ss=ss;
    }



    @Override
    public void wasAdded() {
        super.wasAdded();

        Image b2Image =  PlayN.assets().getImage("images/b2.png");
        ImageLayer b2Layer = PlayN.graphics().createImageLayer(b2Image);
        Image backImage = PlayN.assets().getImage("images/back.png");
        ImageLayer backLayer = PlayN.graphics().createImageLayer(backImage);
        b2Image.addCallback(new Callback<Image>(){
            @Override
            public void onSuccess(Image result){

            }
            @Override
            public void onFailure(Throwable cause){

            }
        });
        backLayer.setTranslation(0f,400f);
        layer.add(b2Layer);
        layer.add(backLayer);

        backLayer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                super.onPointerEnd(event);
                ss.remove(TestScreen.this);
            }
        });
    }



   /* private Root root;
    @Override
    public void wasShown() {
        super.wasShown();
        root = iface.createRoot(
                vertical().gap(15),
                newSheet(),layer
        );

        root.addStyles(Style.BACKGROUND
                .is(Background.bordered(0xFFCCCCCC, 0xFF99CCFF, 5)
                        .inset(5, 10)));
        root.setSize(width(),height());

        root.add(new Label("Page 2")
                .addStyles(Style.FONT.is(HomeScreen.TITLE_FONT)));
        root.add(new Button("back").onClick(new UnitSlot(){
            public  void onEmit(){
                ss.remove(TestScreen.this);
            }
        }));


    }*/

    @Override
    public void update(int delta) {


    }
}
