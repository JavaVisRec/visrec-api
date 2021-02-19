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