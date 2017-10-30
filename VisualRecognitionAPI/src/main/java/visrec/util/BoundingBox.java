package visrec.util;

import visrec.classifier.ClassificationResult;

/**
 * This class represents a bounding box over image at specified position, dimensions, label and score.
 * 
 * Ili da imam  ImageClassification result koji nasledjuje ClassificationResult i ima bounding box
 * 
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class BoundingBox extends ClassificationResult {
    private final int x, y, width, height;

    public BoundingBox(String label, float score, int x, int y, int width, int height) {
        super(label, score);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
        
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
            
}