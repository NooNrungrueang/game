package sut.game01.core;

import Sprite.Zealot;
import playn.core.*;
import playn.core.util.Clock;
import sut.game01.core.Screen.HomeScreen;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;

public class MyGame extends Game.Default {


    private ScreenStack ss = new ScreenStack();
    protected  final Clock.Source clock = new Clock.Source(33);


  public MyGame() {
    super(33); // call update every 33ms (30 times per second)
  }



  @Override
  public void init() {
      final Screen home = new HomeScreen(ss);
    ss.push(home);
      PlayN.keyboard().setListener(new Keyboard.Adapter() {
          @Override
          public void onKeyDown(Keyboard.Event event) {
              if(event.key()==Key.ESCAPE){
                  ss.popTo(home);

              }
          }

          @Override
          public void onKeyTyped(Keyboard.TypedEvent event) {

          }

          @Override
          public void onKeyUp(Keyboard.Event event) {

          }
      });
  }

  @Override
  public void update(int delta) {
      ss.update(delta);
  }

  @Override
  public void paint(float alpha) {
    clock.paint(alpha);
     ss.paint(clock);


  }
}
