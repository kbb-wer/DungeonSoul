package com.sample.box;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.sample.box.entities.BoundedCamera;
//import com.sample.box.entities.Info;
import com.sample.box.entities.Sprites;
import com.sample.box.handlers.*;
import com.sample.box.helpers.GameHelper;

import java.util.Random;

import static com.sample.box.handlers.B2DVars.PPM;

import static com.sample.box.utils.Console.log;

/*
* must provide contact and input handlers, camera and manager
*
*/

public class Game implements ApplicationListener {

    public static final String TITLE = "Flaming Fist";
    public static final int V_HEIGHT = 480;
    public static final int V_WIDTH = 640;
    public static final int SCALE =2;

    private boolean isPaused= false;

    private BoundedCamera bCam;
    private OrthographicCamera oCam;

    private GameStateManager gsm;
    private GameInputProcessor gip;
    private GameContactListener gcl;
    private Content resource;

    public static SaveManager saveManager;
//    private Info info;

    public void create(){
        resource = new Content();
        gip = new GameInputProcessor();                         //input listener
        gcl = new GameContactListener();                        //contact listener
        bCam = new BoundedCamera();                             //bounded camera
        bCam.setToOrtho(false, Game.V_WIDTH, Game.V_HEIGHT);
        oCam = new OrthographicCamera();                        //camera
        oCam.setToOrtho(false, Game.V_WIDTH, Game.V_HEIGHT);
        GameHelper.setGame(this); //create ref via helper
        gsm = new GameStateManager();  //managing game state(views)
        saveManager = new SaveManager(false);
    }

    public void render(){
//        Gdx.graphics.setTitle(TITLE + " -- FPS: " + Gdx.graphics.getFramesPerSecond());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.updateAndRender();
    }

    public void dispose(){}

    public void resize(int w, int h){}
    public void pause(){
        isPaused = true;
    }
    public void resume(){
        isPaused = false;
    }

    public GameInputProcessor getGip() {
        return gip;
    }

    public BoundedCamera getbCam() {
        return bCam;
    }

    public OrthographicCamera getoCam() {
        return oCam;
    }

    public GameStateManager getGsm() {
        return gsm;
    }

    public GameContactListener getGcl() {
        return gcl;
    }

    public Content getResource() {
        return resource;
    }

    public boolean isPaused() {
        return isPaused;
    }
}


