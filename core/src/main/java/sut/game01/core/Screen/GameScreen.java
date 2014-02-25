package sut.game01.core.Screen;

import Sprite.Zealot;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.callbacks.DebugDraw;
import org.jbox2d.collision.Manifold;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;

import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.Contact;
import playn.core.*;
import playn.core.util.Clock;
import sut.game01.core.DebugDrawBox2D;
import tripleplay.game.Screen;
import tripleplay.game.ScreenStack;

import javax.xml.bind.Marshaller;

/**
 * Created by N.Rungrueang on 2/4/14.
 */
public class GameScreen extends Screen {
    public static float M_PER_PEXEL =1/26.666667f;
    //size of world
    private static int width = 24;
    private static int height =18;
    private final ScreenStack ss;
    private World world;
    private Boolean showDebugDraw = true;
    private DebugDrawBox2D debugDraw;
    private Zealot z;




    public GameScreen(ScreenStack ss){
        this.ss=ss;
    }

    @Override
    public void wasAdded() {
        super.wasAdded();
        Vec2 gravity = new Vec2(0.0f,10.0f); //ถ้าจะให้วัตถุลอย Vec2 gravity = new Vec2(0.0f,-1.0f);
        world = new World(gravity,true);
        world.setWarmStarting(true);
        world.setAutoClearForces(true);

        Image zImage =  PlayN.assets().getImage("images/dog.png");
        ImageLayer zLayer = PlayN.graphics().createImageLayer(zImage);
        layer.add(zLayer);

        z=new Zealot(world,500f,100f);
        layer.add(z.layer());


        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {

            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold manifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse contactImpulse) {

            }
        });

        if(showDebugDraw){
            CanvasImage image = PlayN.graphics().createImage(
                    (int)(width/GameScreen.M_PER_PEXEL),
                    (int)(height/GameScreen.M_PER_PEXEL));
            layer.add(PlayN.graphics().createImageLayer(image));
            debugDraw = new DebugDrawBox2D();
            debugDraw.setCanvas(image);
            debugDraw.setFlipY(false);
            debugDraw.setStrokeAlpha(150);
            debugDraw.setFillAlpha(75);
            debugDraw.setStrokeWidth(2.0f);
            debugDraw.setFlags(DebugDraw.e_shapeBit|DebugDraw.e_jointBit|DebugDraw.e_aabbBit);
            debugDraw.setCamera(0,0,1f/GameScreen.M_PER_PEXEL);
            world.setDebugDraw(debugDraw);

        }
        Body ground = world.createBody(new BodyDef());
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsEdge(new Vec2(2f,height-2),new Vec2(width-2f,height-2f));
        ground.createFixture(groundShape,0.0f);

         final Body a = creatBox();
        creatBox2();


        zLayer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                super.onPointerEnd(event);
                a.applyLinearImpulse(new Vec2(100f,0f),a.getPosition());
            }
        });


    }


    @Override
    public void update(int delta) {
        super.update(delta);
        world.step(0.033f,10,10);
        z.update(delta);


    }

    @Override
    public void paint(Clock clock) {
        super.paint(clock);
        if(showDebugDraw){
            debugDraw.getCanvas().clear();
            world.drawDebugData();

        z.paint(clock);

        }
    }
    private Body creatBox(){
        BodyDef bf = new BodyDef();
        bf.type = BodyType.DYNAMIC;
        bf.position = new Vec2(0,0);

        Body body = world.createBody(bf);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1f,1f);
        FixtureDef fd = new FixtureDef();
        fd.shape = shape;
        fd.density = 0.1f;
        fd.friction = 0.1f;
        fd.restitution = 1f;
        body.createFixture(fd);
        body.setLinearDamping(0.5f);
        body.setTransform(new Vec2(10f, 0f), 0);
        return body;
    }

    private void creatBox2(){
        BodyDef bf2 = new BodyDef();
        bf2.type = BodyType.DYNAMIC;
        bf2.position = new Vec2(10,10);

        Body body2 = world.createBody(bf2);
        PolygonShape shape2= new PolygonShape();
        shape2.setAsBox(2f,2f);
        FixtureDef fd2 = new FixtureDef();
        fd2.shape = shape2;
        fd2.density=0.1f;
        fd2.friction=0.1f;
        fd2.restitution=1f;
        body2.createFixture(fd2);
        body2.setLinearDamping(0.5f);
        body2.setTransform(new Vec2(15f,5f),0);
    }


}
