/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diverapp;

import java.io.File;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 *
 * @author sedj601
 */
public class Harpoon {
    Bounds bounds;
    double rotation;
    double speed;
    ImageView ivHarpoon = new ImageView();
    AnchorPane parent;
    
    Harpoon(Bounds bounds, double rotation)
    {
        this.bounds = bounds;
        this.rotation = rotation;                
        speed = 60 * 5;          
            
        File harpoonFile = new File("src/img/harpoon.png");
        if(harpoonFile.exists())
        {
            Image harpoonImage = new Image(harpoonFile.toURI().toString());
            ivHarpoon.setImage(harpoonImage);
            ivHarpoon.setId("harpoon");
            ivHarpoon.setX(bounds.getMaxX());
            ivHarpoon.setY(bounds.getMaxY());
            ivHarpoon.setLayoutX(-bounds.getWidth()/2);
            ivHarpoon.setLayoutY(-bounds.getHeight());
            ivHarpoon.setEffect(new DropShadow( 20, Color.AQUA ));     
        }  
    }  
    
    void shoot(double t)
    {   
        ivHarpoon.setRotate(rotation);                               
        ivHarpoon.setX(ivHarpoon.getX() + (speed * (Math.cos(Math.toRadians(rotation)))) * t);
        ivHarpoon.setY(ivHarpoon.getY() + (speed * (Math.sin(Math.toRadians(rotation)))) * t);  
    }
    
    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(ivHarpoon.getBoundsInParent().getMinX(),ivHarpoon.getBoundsInParent().getMinY(), ivHarpoon.getBoundsInParent().getWidth(), ivHarpoon.getBoundsInParent().getHeight());
    }
    
    public boolean intersect(Squid s)
    {
        return s.getBoundary().intersects(this.getBoundary() );
    }
    
    void setSpeed(double speed)
    {
        this.speed = speed;
    }
    
    void addToScene(AnchorPane ap)
    {
        ap.getChildren().add(this.ivHarpoon);
    }
    
    ImageView getHarpoon()
    {
        return ivHarpoon;
    }
}
