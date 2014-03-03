package sut.game01.core.Screen;

import playn.core.*;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;
import tripleplay.ui.Label;
import tripleplay.ui.Root;
import tripleplay.ui.Style;

import static playn.core.PlayN.graphics;
import static tripleplay.ui.SimpleStyles.newSheet;
import static tripleplay.ui.layout.AxisLayout.vertical;

/**
 * Created by N.Rungrueang on 3/2/14.
 */
public class HeightScoreScreen extends Screen {

    private ScreenStack ss;
    private Root root;

    public HeightScoreScreen(ScreenStack ss){
        this.ss=ss;
    }

    public static final Font TITLE_FONT =
            graphics().createFont(
                    "Helvetica",
                    Font.Style.BOLD,50
            );

    @Override
    public void wasAdded() {
        super.wasAdded();

        Image backImage = PlayN.assets().getImage("images/back.png");
        ImageLayer backLayer = PlayN.graphics().createImageLayer(backImage);
        Image HeightScoreImage = PlayN.assets().getImage("images/HeightScore.png");
        ImageLayer HeightScoreLayer = PlayN.graphics().createImageLayer(HeightScoreImage);
        Image HighScoreButtonImage = PlayN.assets().getImage("images/HighScoresButton.png");
        ImageLayer HighScoreButtonLayer = PlayN.graphics().createImageLayer(HighScoreButtonImage);
        backLayer.setTranslation(0f,400f);
        HighScoreButtonLayer.setTranslation(200f,50f);
        layer.add(HeightScoreLayer);
        layer.add(backLayer);
        layer.add(HighScoreButtonLayer);



        backLayer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                super.onPointerEnd(event);
                ss.remove(HeightScoreScreen.this);
            }
        });
    }

    @Override
    public void wasShown() {
        super.wasShown();
        root.setSize(width(),height());
        root.add(new Label("test score")
                .addStyles(Style.FONT.is(HeightScoreScreen.TITLE_FONT).style.COLOR.is(0xFFFFFFCC)));

    }
}
