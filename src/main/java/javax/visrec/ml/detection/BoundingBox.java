/**
 * Visual Recognition API for Java, JSR381
 * Copyright (C) 2020  Zoran Sevarac, Frank Greco
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
 
package javax.visrec.ml.detection;

/**
 * This class represents a bounding box over image at specified position, dimensions, label and score.
 * 
 * @since 1.0
 */
public class BoundingBox {
    private int id;
    private final float x, y, width, height;
    private final String label;
    private final float score;


    public BoundingBox(String label, float score, int x, int y, float width, float height) {
        this.label =label;
        this.score  = score;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
          
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getScore() {
        return score;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "BoundingBox{" + "id=" + id + ", x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", label=" + label + ", score=" + score + '}';
    }
    
    
       
}