package Sprite;


import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import playn.core.Layer;
import playn.core.PlayN;
import playn.core.Pointer;
import playn.core.util.Callback;
import playn.core.util.Clock;
import sut.game01.core.Screen.GameScreen;


/**
 * Created by N.Rungrueang on 1/28/14.
 */
public class Zealot {

    private Sprite sprite;
    private int spriteIndex = 0;
    private  boolean hasLoded = false;
    private Body body;

    public enum State{
        IDLE,RUN,DIE
    };

    private State state = State.IDLE;
    private int e = 0;
    private int offset = 0;

    private int p=100;
    private int t=0;


    public Zealot(final World world,final float x, final float y){
        this.sprite = SpriteLoader.getSprite("images/zealot.json");
        sprite.addCallback(new Callback<Sprite>() {
            @Override
            public void onSuccess(Sprite result) {
                sprite.setSprite(spriteIndex);
                sprite.layer().setOrigin(sprite.width()/2f,sprite.height()/2f);
                sprite.layer().setTranslation(x,y);
                body = initPhysicsBody(world,GameScreen.M_PER_PEXEL*x,GameScreen.M_PER_PEXEL*y);

                hasLoded = true;

                sprite.layer().addListener(new Pointer.Adapter(){
                    @Override
                    public void onPointerEnd(Pointer.Event event) {
                        state = State.DIE;
                        spriteIndex = -1;
                        e=0;
                    }
                });
            }

            @Override
            public void onFailure(Throwable cause) {
                PlayN.log().error("Error loading image!",cause);
            }
        });
    }
    public void update(int delta){
        if(!hasLoded)return;
        e+=delta;
       // t+=delta;

        if(t==1000){p-=50; t=0;}
        if(p==0){state = State.DIE;}

        if(e>150){
            switch (state){
                case IDLE : offset = 0;
                    break;
                case RUN : offset = 4;
                    break;
                case DIE : offset = 8;


                    if(spriteIndex==10){
                        state = State.IDLE;
                    }
                    break;

            }
            if(spriteIndex==11){spriteIndex=spriteIndex+0;}
                else{
            spriteIndex = offset + ((spriteIndex + 1)%4);
            sprite.setSprite(spriteIndex);
            e=0;}


        }
    }

    public void paint(Clock clock){
        if(!hasLoded)return;
        sprite.layer().setTranslation((body.getPosition().x/GameScreen.M_PER_PEXEL)-10,body.getPosition().y/GameScreen.M_PER_PEXEL);
    }

    public Layer layer(){
        return sprite.layer();
    }

    private Body initPhysicsBody(World world,float x,float y){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position = new Vec2(0,0);
        Body body = world.createBody(bodyDef);


        PolygonShape shape = new PolygonShape();
        shape.setAsBox(56* GameScreen.M_PER_PEXEL/2,sprite.layer().height()*GameScreen.M_PER_PEXEL/2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape=shape;
        fixtureDef.density=0.4f;
        fixtureDef.friction = 0.1f;
        //fixtureDef.restitution=0.35f;
        body.createFixture(fixtureDef);

        body.setLinearDamping(0.2f);
        body.setTransform(new Vec2(x,y),0f);
        return body;

    }


}
