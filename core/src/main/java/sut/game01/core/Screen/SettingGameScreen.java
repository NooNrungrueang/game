package sut.game01.core.Screen;

import playn.core.*;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;
import tripleplay.ui.Interface;
import tripleplay.ui.Label;
import tripleplay.ui.Root;
import tripleplay.ui.Style;

import static playn.core.PlayN.graphics;
import static tripleplay.ui.SimpleStyles.newSheet;
import static tripleplay.ui.layout.AxisLayout.vertical;

/**
 * Created by N.Rungrueang on 2/26/14.
 */
public class SettingGameScreen extends UIScreen {
    private final ScreenStack ss;
    private Root root;

    public SettingGameScreen(ScreenStack ss){
        this.ss=ss;
    }

    @Override
    public void wasAdded() {
        super.wasAdded();

        Image backImage = PlayN.assets().getImage("images/back.png");
        ImageLayer backLayer = PlayN.graphics().createImageLayer(backImage);
        Image settingBGImage = PlayN.assets().getImage("images/settingBg.png");
        ImageLayer settingBGLayer = PlayN.graphics().createImageLayer(settingBGImage);
        Image settingImage = PlayN.assets().getImage("images/settingBT.png");
        ImageLayer settingLayer = PlayN.graphics().createImageLayer(settingImage);
        Image soundOnImage = PlayN.assets().getImage("images/sound_on.png");
        ImageLayer soundOnLayer = PlayN.graphics().createImageLayer(soundOnImage);
        Image soundOffImage = PlayN.assets().getImage("images/sound_off.png");
        ImageLayer soundOffLayer = PlayN.graphics().createImageLayer(soundOffImage);
        Image soundOnImage2 = PlayN.assets().getImage("images/sound_on.png");
        ImageLayer soundOnLayer2 = PlayN.graphics().createImageLayer(soundOnImage2);
        Image soundOffImage2 = PlayN.assets().getImage("images/sound_off.png");
        ImageLayer soundOffLayer2 = PlayN.graphics().createImageLayer(soundOffImage2);

        settingLayer.setTranslation(180f,30f);
        backLayer.setTranslation(0f,400f);
        soundOnLayer.setTranslation(335f,180f);
        soundOffLayer.setTranslation(550f,180f);
        soundOnLayer2.setTranslation(335f,280f);
        soundOffLayer2.setTranslation(550f,280f);
        layer.add(settingBGLayer);
        layer.add(settingLayer);
        layer.add(backLayer);
        layer.add(soundOnLayer);
        layer.add(soundOffLayer);
        layer.add(soundOnLayer2);
        layer.add(soundOffLayer2);

        backLayer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                super.onPointerEnd(event);
                ss.remove(SettingGameScreen.this);
            }
        });
    }

    @Override
    public void wasShown() {
        super.wasShown();
        root = iface.createRoot(
                vertical().gap(15),
                newSheet(),layer
        );

        root.add(new Label("Let 's eat Sweet")
                .addStyles(Style.FONT.is(HomeScreen.TITLE_FONT).style.COLOR.is(0xFFFFFFCC)));
    }
}
