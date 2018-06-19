/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diverapp;

import java.io.File;
import java.util.Random;
import javafx.geometry.Rectangle2D;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 *
 * @author Sedrick
 */
public class Squid {    
    double speed = 120;     
    ImageView ivSquid = new ImageView();
    Random rand = new Random();
    int direction;
    
    Squid(int id)
    {
        ivSquid.setId("squid" + id);
        ivSquid.setEffect(new DropShadow( 20, Color.AQUA ));
        speed = (rand.nextDouble())  * 120;
        File squidFile = new File("src/img/squid/squid0.png");
        if(squidFile.exists())
        {
            Image squidImage = new Image(squidFile.toURI().toString());
            ivSquid.setImage(squidImage);
            ivSquid.setX(800);
            ivSquid.setY(rand.nextInt(798) + 1);
        }                
    }
    
    void addToScene(AnchorPane ap)
    {
        ap.getChildren().add(this.ivSquid);
    }
    
    void moveSquid(double t)
    {
        ivSquid.setX(ivSquid.getX() - (speed * t)) ;
    }
    
    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(ivSquid.getBoundsInParent().getMinX(),ivSquid.getBoundsInParent().getMinY(), ivSquid.getBoundsInParent().getWidth(), ivSquid.getBoundsInParent().getHeight());
    }
    
    public boolean intersect(Harpoon s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }
    
    ImageView getSquid()
    {
        return ivSquid;
    }
}
