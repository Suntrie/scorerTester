package model;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class Sprite {

    private Image image;

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    private double positionX;
    private double positionY;

    public Image getImage() {
        return image;
    }

    public Sprite(String imagePath){
        positionX = 0;
        positionY = 0;
        setImage(imagePath);
    }

    public void setImage(Image i){
        image = i;
    }



    public void setImage(String filename){
        Image i = new Image(filename, 100, 100, true, true);
        setImage(i);
    }



    public void setPosition(double x, double y){
        positionX = x;
        positionY = y;
    }

    public void setPositionOfCenter(double x, double y){
        positionX =x-image.getWidth()/2;
        positionY = y-image.getHeight()/2;
    }



    public Rectangle2D getBoundary(){
        return new Rectangle2D(positionX, positionY, image.getWidth(), image.getHeight());
    }



    public boolean intersects(Sprite s){
        return s.getBoundary().intersects( this.getBoundary() );
    }



    public String toString(){
        return " Position: [" + positionX + "," + positionY + "]";
    }
}
