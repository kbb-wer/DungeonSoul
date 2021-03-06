package com.sample.box.factory;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.sample.box.character.Character;
import com.sample.box.character.Warrior;
import com.sample.box.entities.PositionedFixture;
import com.sample.box.entities.Sprites;
import com.sample.box.handlers.B2DVars;
import com.sample.box.helpers.GameHelper;

import java.util.List;

import static com.sample.box.handlers.B2DVars.PPM;

public class PlayerFactory {

    public static List<Character> players;

    public static PositionedFixture fixture;

    public static Character buildWarrior(World world){
        //create player body
        Body body = build(world);
        //link resource
        GameHelper.getGame().getResource().loadTexture(Sprites.WARRIOR_LEFT,"player_l");
        GameHelper.getGame().getResource().loadTexture(Sprites.WARRIOR,"player");
        GameHelper.getGame().getResource().loadTexture(Sprites.LIFE_CONTAINERS,"life");
        Warrior warrior = new Warrior(body);
        warrior.addFixture("torch",fixture);
        body.setUserData(warrior);
        GameHelper.getGame().getGip().setWarrior(warrior);
        return warrior;
    }

    private static Body build(World world){
        BodyDef bdef = new BodyDef();       //body definition
        FixtureDef fdef = new FixtureDef();    //body fixture definition

        //body type def
        bdef.position.set(0.5f, 0.5f);
        bdef.type = BodyDef.BodyType.DynamicBody;

        //create container and shape
        Body body = world.createBody(bdef);
        PolygonShape ps = new PolygonShape();

        //player body
        ps.setAsBox(8/PPM,12/PPM,new Vector2(-2/PPM,0),0);
        fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
        fdef.filter.maskBits = B2DVars.BIT_GROUND | B2DVars.BIT_POINT | B2DVars.BIT_WALL | B2DVars.BIT_DESTROYABLE | B2DVars.BIT_CONTAINER;
        fdef.shape = ps;
        body.createFixture(fdef).setUserData("player");

        // create foot sensor
        ps.setAsBox(8/PPM,1/PPM,new Vector2(-2/PPM,-13/PPM),0);
        fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
        fdef.filter.maskBits = B2DVars.BIT_GROUND;
        fdef.shape = ps;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("footSensor");

        // create left side sensor
        ps.setAsBox(2/PPM,10/PPM,new Vector2(-12/PPM,0),0);
        fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
        fdef.filter.maskBits = B2DVars.BIT_WALL | B2DVars.BIT_GROUND;
        fdef.shape = ps;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("leftSensor");

        //create right side sensor
        ps.setAsBox(2/PPM,10/PPM,new Vector2(8/PPM,0),0);
        fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
        fdef.filter.maskBits = B2DVars.BIT_WALL | B2DVars.BIT_GROUND;
        fdef.shape = ps;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("rightSensor");

        //create right hit range sensor
        ps.setAsBox(4/PPM,10/PPM,new Vector2(10/PPM,0),0);
        fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
        fdef.filter.maskBits = B2DVars.BIT_DESTROYABLE;
        fdef.shape = ps;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("enemyRightSensor");

        // create left hit range sensor
        ps.setAsBox(4/PPM,10/PPM,new Vector2(-14/PPM,0),0);
        fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
        fdef.filter.maskBits = B2DVars.BIT_DESTROYABLE;
        fdef.shape = ps;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData("enemyLeftSensor");

        // create torch fixture
        CircleShape cshape = new CircleShape();
        GameHelper.getGame().getResource().loadTexture(Sprites.TORCH,"torch");

        ps.dispose();
        cshape.setRadius(0.04f);
        Vector2 pos = new Vector2(.55f,.25f);
        Vector2 n = new Vector2(.11f, .11f);        //first torch shape position
        cshape.setPosition(n);

        fdef.shape = cshape;
        fdef.isSensor = true;
        Fixture f = body.createFixture(fdef);
        f.setUserData(GameHelper.getGame().getResource().getTexture("torch"));
        fixture = new PositionedFixture(pos,f);
        return body;
    }


}
