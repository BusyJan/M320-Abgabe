/**
 * A cloud composed of multiple circles that can move together and draw itself on a canvas.
 * Demonstrates composition: a Cloud owns its Circle parts.
 */
public class Cloud
{
    private final Circle[] circles;
    private int xPosition;
    private int yPosition;
    private String color;

    /**
     * Create a cloud at the given position with a default size and color.
     */
    public Cloud(int x, int y)
    {
        this(x, y, 1.0);
    }

    /**
     * Create a cloud at the given position with a scale factor.
     * scale > 1.0 makes it bigger, between 0 and 1.0 makes it smaller.
     */
    public Cloud(int x, int y, double scale)
    {
        xPosition = x;
        yPosition = y;
        color = "white";

        circles = new Circle[4];
        for(int i=0;i<circles.length;i++){
            circles[i] = new Circle();
            circles[i].changeColor(color);
        }
        // Arrange circles relative to cloud origin (xPosition, yPosition)
        // Use different diameters and offsets to get a fluffy shape
        int[] baseSizes = { 40, 50, 36, 44 };
        int[] baseDx = { 0, 25, 50, 20 };
        int[] baseDy = { 0, -10, 0, 12 };

        for(int i=0;i<circles.length;i++){
            int size = (int)Math.round(baseSizes[i] * scale);
            int dx = (int)Math.round(baseDx[i] * scale);
            int dy = (int)Math.round(baseDy[i] * scale);
            circles[i].changeSize(size);
            // First move to cloud origin relative to default
            circles[i].moveHorizontal(xPosition - 230); // Circle default x is 230
            circles[i].moveVertical(yPosition - 90);    // Circle default y is 90
            // Then apply relative offsets to shape the cloud
            circles[i].moveHorizontal(dx);
            circles[i].moveVertical(dy);
        }
    }

    /** Make this cloud visible. */
    public void makeVisible()
    {
        for(Circle c : circles){
            c.makeVisible();
        }
    }

    /** Make this cloud invisible. */
    public void makeInvisible()
    {
        for(Circle c : circles){
            c.makeInvisible();
        }
    }

    /** Change the color of the whole cloud. */
    public void changeColor(String newColor)
    {
        color = newColor;
        for(Circle c : circles){
            c.changeColor(color);
        }
    }

    /** Set the absolute position of the cloud's origin. */
    public void setPosition(int x, int y)
    {
        int dx = x - xPosition;
        int dy = y - yPosition;
        moveHorizontal(dx);
        moveVertical(dy);
    }

    /** Move the cloud horizontally by the given distance. */
    public void moveHorizontal(int distance)
    {
        for(Circle c : circles){
            c.moveHorizontal(distance);
        }
        xPosition += distance;
    }

    /** Move the cloud vertically by the given distance. */
    public void moveVertical(int distance)
    {
        for(Circle c : circles){
            c.moveVertical(distance);
        }
        yPosition += distance;
    }

    /** Slowly move the cloud horizontally by the given distance. */
    public void slowMoveHorizontal(int distance)
    {
        int delta;
        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }
        for(int i = 0; i < distance; i++){
            for(Circle c : circles){
                c.moveHorizontal(delta);
            }
            xPosition += delta;
        }
    }

    /**
     * Slowly move the cloud horizontally by the given distance with extra delay per pixel.
     */
    public void slowMoveHorizontal(int distance, int extraDelay)
    {
        int delta;
        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }
        for(int i = 0; i < distance; i++){
            for(Circle c : circles){
                c.moveHorizontal(delta);
            }
            xPosition += delta;
            if(extraDelay > 0){
                Canvas.getCanvas().wait(extraDelay);
            }
        }
    }

    /** Slowly move the cloud vertically by the given distance. */
    public void slowMoveVertical(int distance)
    {
        int delta;
        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }
        for(int i = 0; i < distance; i++){
            for(Circle c : circles){
                c.moveVertical(delta);
            }
            yPosition += delta;
        }
    }
}


