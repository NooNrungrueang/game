package sut.game01.core.Screen;

import playn.core.Font;
import static playn.core.PlayN.*; //import ทุกอย่างของ PlayN

import react.UnitSlot;
import tripleplay.game.ScreenStack;
import tripleplay.game.UIScreen;
import tripleplay.ui.*;


import static tripleplay.ui.SimpleStyles.*;
import static tripleplay.ui.layout.AxisLayout.*;


/**
 * Created by N.Rungrueang on 1/21/14.
 */
public class HomeScreen extends UIScreen {

    public  static  final  Font TITLE_FONT =
            graphics().createFont(
              "Helvetica",
               Font.Style.BOLD,24
            );

    private final ScreenStack ss;
    private Root root;

    public  HomeScreen(ScreenStack ss){
        this.ss=ss;
    }

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
        
        root.add(new Label("Start Home")
            .addStyles(Style.FONT.is(HomeScreen.TITLE_FONT)));
        root.add(new Button("Start").onClick(new UnitSlot(){
            public  void onEmit(){
                ss.push(new GameScreen(ss));
            }
        }));


    }
}
