/**
 * This class represents a simple picture. You can draw the picture using
 * the draw method. But wait, there's more: being an electronic picture, it
 * can be changed. You can set it to black-and-white display and back to
 * colors (only after it's been drawn, of course).
 *
 * This class was written as an early example for teaching Java with BlueJ.
 * 
 * @author  Michael K�lling and David J. Barnes
 * @version 2011.07.31
 */
public class Picture
{
    private Square sky;
    private Square wall;
    private Square window;
    private Triangle roof;
    private Circle sun;
    private Cloud cloud;
    private Person person;

    /**
     * Constructor for objects of class Picture
     */
    public Picture()
    {
        // nothing to do... instance variables are automatically set to null
    }

    /**
     * Draw this picture.
     */
    public void draw()
    {
        // Draw sky background so white cloud is visible
        sky = new Square();
        sky.changeColor("blue");
        sky.changeSize(1000);
        sky.moveHorizontal(-500);
        sky.moveVertical(-500);
        sky.makeVisible();

        wall = new Square();
        wall.moveHorizontal(-140);
        wall.moveVertical(20);
        wall.changeSize(120);
        wall.makeVisible();
        
        window = new Square();
        window.changeColor("blue");
        window.moveHorizontal(-120);
        window.moveVertical(40);
        window.changeSize(40);
        window.makeVisible();

        roof = new Triangle();  
        roof.changeSize(60, 180);
        roof.moveHorizontal(20);
        roof.moveVertical(-60);
        roof.makeVisible();

        sun = new Circle();
        sun.changeColor("yellow");
        sun.moveHorizontal(100);
        sun.moveVertical(-40);
        sun.changeSize(80);
        sun.makeVisible();

        // Create two clouds and animate them concurrently
        cloud = new Cloud(20, 30);
        Cloud smallCloud = new Cloud(-80, 60, 0.7);
        cloud.makeVisible();
        smallCloud.makeVisible();

        int totalSteps = 420;
        for(int step = 0; step < totalSteps; step++){
            if(step < 360){
                cloud.moveHorizontal(1);
            }
            // move smaller cloud every other step for slower effect
            if(step < 420 && step % 2 == 0){
                smallCloud.moveHorizontal(1);
            }
            Canvas.getCanvas().wait(10);
        }

        // After the cloud passes, move a person towards the house (aggregation)
        person = new Person();
        person.moveHorizontal(-180); // start further left
        person.makeVisible();
        person.slowMoveHorizontal(120);
    }

    /**
     * Change this picture to black/white display
     */
    public void setBlackAndWhite()
    {
        if (wall != null)   // only if it's painted already...
        {
            wall.changeColor("black");
            window.changeColor("white");
            roof.changeColor("black");
            sun.changeColor("black");
        }
    }

    /**
     * Change this picture to use color display
     */
    public void setColor()
    {
        if (wall != null)   // only if it's painted already...
        {
            wall.changeColor("red");
            window.changeColor("black");
            roof.changeColor("green");
            sun.changeColor("yellow");
        }
    }
}
