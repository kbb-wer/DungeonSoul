package com.sample.box.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureHelper {

    private static TextureAtlas staff = new TextureAtlas(Gdx.files.internal("assets/atlas/items.atlas"));
    private static TextureAtlas container = new TextureAtlas(Gdx.files.internal("assets/atlas/cells.atlas"));

    public static TextureRegion getBottle(){
        return staff.findRegion("bottle");
    }

    public static TextureRegion getScroll(){
        return staff.findRegion("scroll");
    }

    public static TextureRegion getCell(){
        return container.findRegion("cm");
    }
}
