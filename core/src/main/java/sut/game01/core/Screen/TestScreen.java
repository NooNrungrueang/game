package sut.game01.core.Screen;

import Sprite.Sprite;
import Sprite.Zealot;
import org.jbox2d.dynamics.World;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.PlayN;
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
   // private Zealot z=new Zealot(100f,100f);


    @Override
    public void wasAdded() {
        super.wasAdded();

        Image b2Image =  PlayN.assets().getImage("images/b2.png");
        //Image dogImage = PlayN.assets().getImage("images/dog.png");


        b2Image.addCallback(new Callback<Image>(){
            @Override
            public void onSuccess(Image result){

            }
            @Override
            public void onFailure(Throwable cause){

            }
        });
        ImageLayer b2Layer = PlayN.graphics().createImageLayer(b2Image);
        /*ImageLayer dogLayer = PlayN.graphics().createImageLayer(dogImage);
        layer.add(dogLayer);*/
        layer.add(b2Layer);
       //z=new Zealot(100f,100f);
       // layer.add(z.layer());




    }



    private Root root;
   /* @Override
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
       // z.update(delta);

    }
}
