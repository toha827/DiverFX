/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diverapp;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author blj0011
 */
public class FXMLDocumentController implements Initializable {
    @FXML private AnchorPane apMain;  
    
    Set<String> keyInput = new HashSet();  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Diver diver = new Diver();
        diver.addToScene(apMain);
        List<Squid> squidContainer = new ArrayList();
        List<Harpoon> harpoonContainer = new ArrayList();
        
        for(int i = 0; i < 20; i++)
        {
            Squid squid = new Squid(i);
            squid.addToScene(apMain);
            squidContainer.add(squid);             
        }       
        
        diver.getDiver().setFocusTraversable(true);        
        diver.getDiver().setOnKeyPressed((KeyEvent event) -> {
            keyInput.add(event.getCode().toString());
        });
        
        diver.getDiver().setOnKeyReleased((KeyEvent event) -> {
            if(!event.getCode().equals(KeyCode.S))
            {
                keyInput.remove(event.getCode().toString());
            }
        }); 
                
        final LongValue lastNanoTime = new LongValue(System.nanoTime());
        
        AnimationTimer timer = new AnimationTimer(){
            @Override
            public void handle(long now) {
                double t = (now - lastNanoTime.getLongValue()) / 1000000000.0;
                lastNanoTime.setLongValue(now);
                  
                if(keyInput.contains("S"))
                {
                    Harpoon harpoon = new Harpoon(diver.getDiver().getBoundsInLocal(), diver.getRotation());                    
                    harpoonContainer.add(harpoon);                    
                    harpoon.addToScene(apMain);
                    keyInput.remove("S");
                }
               
                harpoonContainer.forEach((item) -> {item.shoot(t);});
                
                if(keyInput.contains("UP")){diver.moveUp(t);}
                if(keyInput.contains("DOWN")){diver.moveDown(t);}
                if(keyInput.contains("LEFT")){diver.moveLeft(t);}
                if(keyInput.contains("RIGHT")){diver.moveRight(t);}                  
                
                squidContainer.forEach((item) -> {item.moveSquid(t);});
                
                Iterator<Harpoon> harpoonIter = harpoonContainer.listIterator();
                while(harpoonIter.hasNext())
                {
                    Harpoon tempHarpoon = harpoonIter.next();
                    Iterator<Squid> squidIter = squidContainer.listIterator();
                    while(squidIter.hasNext())
                    {
                        Squid tempSquid = squidIter.next();
                        if(tempHarpoon.intersect(tempSquid))
                        {
                            squidIter.remove();
                            harpoonIter.remove();
                            apMain.getChildren().removeAll(tempSquid.getSquid(), tempHarpoon.getHarpoon());
                        }
                    }
                }
            }            
        };
        timer.start();
    }
}