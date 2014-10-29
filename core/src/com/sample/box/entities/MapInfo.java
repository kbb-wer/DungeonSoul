package com.sample.box.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.sample.box.handlers.Background;

public class MapInfo {
    int tileMapWidth;                   //map width
    int tileMapHeight;                  //map height
    float tileSize;
    Texture background;           //background
    Array<Point> points;

    public int getTileMapWidth() {
        return tileMapWidth;
    }

    public void setTileMapWidth(int tileMapWidth) {
        this.tileMapWidth = tileMapWidth;
    }

    public int getTileMapHeight() {
        return tileMapHeight;
    }

    public void setTileMapHeight(int tileMapHeight) {
        this.tileMapHeight = tileMapHeight;
    }

    public float getTileSize() {
        return tileSize;
    }

    public void setTileSize(float tileSize) {
        this.tileSize = tileSize;
    }

    public Texture getBackground() {
        return background;
    }

    public void setBackground(Texture background) {
        this.background = background;
    }

    public Array<Point> getPoints() {
        return points;
    }

    public void setPoints(Array<Point> points) {
        this.points = points;
    }
}