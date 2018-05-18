package controller;

import model.Sprite;
import view.Main;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Controller {

    private Main main;

    public Set<Sprite> getSources() {
        return sources;
    }

    private Set<Sprite> sources=new HashSet<>();

    public Controller(Main main, String ... sourcesImagesPaths){

        this.main=main;

        double x=0;
        double y=0;

        for (String imagePath: sourcesImagesPaths){
            Sprite sprite=new Sprite(imagePath);
            sprite.setPosition(x,y);
            x=x+sprite.getBoundary().getWidth();
            sources.add(sprite);
        }

    }

    public Optional<Sprite> getSourceInPosition(double x, double y){
        for (Sprite source: sources){
            if (source.getBoundary().contains(x,y)){
                return Optional.of(source);
            }
        };
        return Optional.empty();
    }

    public void onDragCurrentSource(Sprite sprite, double x, double y) {
        sprite.setPositionOfCenter(x,y);
        main.repaintScene();
    }

}
