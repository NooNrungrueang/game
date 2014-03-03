package sut.game01.core.Screen;

import Sprite.Sprite;
import Sprite.Zealot;
import Sprite.Boy;
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
    private Boy b;





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

        Image b2Image = PlayN.assets().getImage("images/b2.png");
        ImageLayer b2Layer = PlayN.graphics().createImageLayer(b2Image);
        //Image flowerpushImage =  PlayN.assets().getImage("images/flowerpush.png");
        //ImageLayer flowerpushLayer = PlayN.graphics().createImageLayer(flowerpushImage);
        //layer.add(flowerpushLayer); รูปที่ใช้ใส่แรงให้กล่อง
        Image backImage = PlayN.assets().getImage("images/back.png");
        ImageLayer backLayer = PlayN.graphics().createImageLayer(backImage);


        b = new Boy(world,300f,300f);
        backLayer.setTranslation(0f,400f);

        layer.add(b2Layer);
        layer.add(backLayer);
        layer.add(b.layer());

        backLayer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                super.onPointerEnd(event);
                ss.remove(GameScreen.this);
            }
        });

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
        //================================================================================
        //set ground in world
        Body ground = world.createBody(new BodyDef());
        PolygonShape groundShape = new PolygonShape();
        groundShape.setAsEdge(new Vec2(2f,height-2),new Vec2(width-2f,height-2f));
        ground.createFixture(groundShape,0.0f);
        //================================================================================
        /*apply force to box
        final Body a = creatBox();
        flowerpushLayer.addListener(new Pointer.Adapter(){
            @Override
            public void onPointerEnd(Pointer.Event event) {
                super.onPointerEnd(event);
                a.applyLinearImpulse(new Vec2(100f,0f),a.getPosition());
            }
        });*/
        //================================================================================


    }


    @Override
    public void update(int delta) {
        super.update(delta);
        world.step(0.033f,10,10);
        b.update(delta);
    }

    @Override
    public void paint(Clock clock) {
        super.paint(clock);
        if(showDebugDraw){
            debugDraw.getCanvas().clear();
            world.drawDebugData();
        b.paint(clock);

        }
    }
    //=====creat Box======================================================
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
    //=======================================================================



}
