package one.circles;

import java.awt.*;

public class BackGround{
    private final Color bg;

    public BackGround(Canvas canvas){
        bg = new Color(
                (int)(Math.random() * 255),
                (int)(Math.random() * 255),
                (int)(Math.random() * 255)
        );

    }

    void update(GameCanvas canvas, float deltaTime) {
        canvas.setBackground(bg);

    }
    void render(GameCanvas canvas) {
        canvas.setBackground(bg);
    }
}
