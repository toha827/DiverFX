/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diverapp;

import java.io.File;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 *
 * @author blj0011
 */
public class Diver {
    File diverFile;
    ImageView ivDiver = new ImageView();
    double rotation = 0.0;
    int diverSpeed = 120, diverRotationSpeed = 120;    
     
    Diver()
    {
        diverFile = new File("src/img/diver.png");
        if(diverFile.exists())
        {
            Image diverImage = new Image(diverFile.toURI().toString());
            ivDiver.setId("diver");
            ivDiver.setImage(diverImage);
            ivDiver.setX(0);
            ivDiver.setY(0);
            ivDiver.setEffect(new DropShadow( 20, Color.AQUA ));
            System.out.println("Diver exist!");
        } 
    }
    
    ImageView getDiver()
    {
        return ivDiver;
    }
    
    void addToScene(AnchorPane ap)
    {
        ap.getChildren().add(this.ivDiver);
    }
    
    boolean intersect(Node node)
    {
        return node.intersects(this.ivDiver.getBoundsInLocal());
    }
    
    void moveUp(double t)
    {
        if(rotation == -360){rotation = 0;} ivDiver.setRotate(rotation -= diverRotationSpeed * t);
    }
    
    void moveDown(double t)
    {
        if(rotation == 360){rotation = 0;} ivDiver.setRotate(rotation += diverRotationSpeed * t);
    }
    
    void moveLeft(double t)
    {
        ivDiver.setX(ivDiver.getX() - (diverSpeed * (Math.cos(Math.toRadians(rotation)))) * t);
        ivDiver.setY(ivDiver.getY() - (diverSpeed * (Math.sin(Math.toRadians(rotation)))) * t);
    }
    
    void moveRight(double t)
    {
        ivDiver.setX(ivDiver.getX() + (diverSpeed * (Math.cos(Math.toRadians(rotation)))) * t);
        ivDiver.setY(ivDiver.getY() + (diverSpeed * (Math.sin(Math.toRadians(rotation)))) * t);
    }
    
    double getRotation()
    {
        return this.rotation;
    }
}
