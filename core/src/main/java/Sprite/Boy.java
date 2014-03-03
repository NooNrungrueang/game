package Sprite;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import playn.core.*;
import playn.core.util.Callback;
import playn.core.util.Clock;
import sut.game01.core.Screen.GameScreen;

/**
 * Created by N.Rungrueang on 3/2/14.
 */
public class Boy  {

    private Sprite sprite;
    private int sptite_index = 0;
    private  boolean hasLoded = false;
    private Body boybody;

    public enum State{IDLE,WALK_RIGHT,WALK_LEFT,JUMP};

    private State state = State.IDLE;
    private int e = 0;
    private int offset = 0;


    public Boy(final World world,final float x,final float y){
        this.sprite = SpriteLoader.getSprite("images/boy.json");
        sprite.addCallback(new Callback<Sprite>() {
            @Override
            public void onSuccess(Sprite result) {
                sprite.setSprite(sptite_index);
                sprite.layer().setOrigin(sprite.width() / 2f, sprite.height() / 2f);
                sprite.layer().setTranslation(x, y);
                boybody = initPhysicsBody(world, GameScreen.M_PER_PEXEL*x,GameScreen.M_PER_PEXEL*y);

                hasLoded = true;

                PlayN.keyboard().setListener(new Keyboard.Adapter(){
                    @Override
                    public void onKeyUp(Keyboard.Event event) {
                        super.onKeyUp(event);
                        if(event.key()== Key.RIGHT){
                            state = State.WALK_LEFT;
                            sptite_index = -1;
                            e = 0;
                            boybody.applyForce(new Vec2(150f,0f),boybody.getPosition());
                        }
                        if(event.key()==Key.LEFT){
                            state = State.WALK_RIGHT;
                            sptite_index=-1;
                            e=0;
                            boybody.applyForce(new Vec2(-150f,0f),boybody.getPosition());
                        }
                        if(event.key()==Key.UP){
                            state = State.JUMP;
                            sptite_index = -1;
                            e=0;
                            boybody.applyForce(new Vec2(150f,3000f),boybody.getPosition());
                        }


                    }
                });


            }


            @Override
            public void onFailure(Throwable cause) {

            }
        });


    }

    public void update(int delta){
        if(!hasLoded)return;
        e+=delta;
        if(e>150){
            switch (state){
                case IDLE: offset = 0;
                    break;
                case WALK_LEFT:  offset = 4;
                    if(sptite_index==6){
                        state = State.IDLE;
                    }
                    break;
                case WALK_RIGHT: offset = 8;
                    if(sptite_index==10){
                        state = State.IDLE;
                    }
                    break;
                case JUMP: offset=12;
                    if(sptite_index==14){
                        state = State.IDLE;
                    }
                default:
                    state = State.IDLE;
            }
            if(sptite_index==11){sptite_index=sptite_index+0;}
            else{
                sptite_index = offset + ((sptite_index + 1)%4);
                sprite.setSprite(sptite_index);
                e=0;}


        }
    }

    public void paint(Clock clock){
        if(!hasLoded)return;
        sprite.layer().setTranslation((boybody.getPosition().x/GameScreen.M_PER_PEXEL)-10,boybody.getPosition().y/GameScreen.M_PER_PEXEL);
        sprite.layer().setRotation(boybody.getAngle());
    }

    public Layer layer(){
        return sprite.layer();
    }

    public Body getBody(){
        return this.boybody;
    }

    private Body initPhysicsBody(World world,float x,float y){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position = new Vec2(0,0);
        Body body = world.createBody(bodyDef);

        ///EdgeShape shape = new EdgeShape();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(56* GameScreen.M_PER_PEXEL/2,sprite.layer().height()*GameScreen.M_PER_PEXEL/2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape=shape;
        fixtureDef.density=0.4f;
        fixtureDef.friction = 0.1f;
        fixtureDef.restitution=0.35f;
        body.createFixture(fixtureDef);

        body.setLinearDamping(0.2f);
        body.setTransform(new Vec2(x,y),0f);
        return body;

    }

}
