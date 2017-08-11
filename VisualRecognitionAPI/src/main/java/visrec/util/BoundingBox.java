package visrec.util;

/**
 * This class represents a bounding box over image at specified position, dimensions, label and score.
 * 
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class BoundingBox {
    private final int x, y, width, height;
    private String label;
    private double score;

    public BoundingBox(int x, int y, int width, int height) {
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
            
}