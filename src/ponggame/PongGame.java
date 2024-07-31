package ponggame;

import processing.core.PApplet;

/**
 * @author Renan Silva -> @renans2 on github
 */
public class PongGame {
    private final PApplet p;
    private final Paddle paddle1;
    private int p1Score = 0;
    private final Paddle paddle2;
    private int p2Score = 0;
    private final Ball ball;
    private final float startingBallMaxYSpeed;
    private float ballMaxYSpeed;

    public PongGame(PApplet parent,
                    int ballDiameter,
                    int ballXSpeed,
                    int ballMaxYSpeed,
                    int paddleHeight,
                    int paddleWidth,
                    int paddleSpeed) {
        p = parent;
        paddle1 = new Paddle(p, paddleHeight, paddleWidth, paddleSpeed, "left");
        paddle2 = new Paddle(p, paddleHeight, paddleWidth, paddleSpeed, "right");
        ball = new Ball(p, ballDiameter, ballXSpeed);
        startingBallMaxYSpeed = ballMaxYSpeed;
        this.ballMaxYSpeed = startingBallMaxYSpeed;
    }

    public void update(boolean[] keys) {
        paddle1.update(keys[0], keys[1]);
        paddle2.update(keys[2], keys[3]);
        ball.update();

        bounceIfCollides();
        resetIfGoesOutOfBounds();
    }

    public void draw() {
        paddle1.draw();
        paddle2.draw();
        ball.draw();
        drawScores();
        drawCentralLine();
    }

    private void bounceIfCollides() {
        if(hitsTopOrBottomBorder())
            ball.reflectY();

        if(hitsPaddle()){
            ball.reflectX();
            ball.setNewYSpeed(getNewY());
            ballMaxYSpeed += 0.2f;
        }
    }

    private boolean hitsTopOrBottomBorder() {
        if(ball.isGoingDown())
            return ball.getY() + ball.getRadius() > p.height;

        else
            return ball.getY() - ball.getRadius() < 0;
    }

    private boolean hitsPaddle() {
        if(ball.isGoingRight())
            return ball.getX() + ball.getRadius() > paddle2.getX() &&
                   ball.getY() + ball.getRadius() > paddle2.getY() &&
                   ball.getY() - ball.getRadius() < paddle2.getY() + paddle2.getHeight();

        else
            return ball.getX() - ball.getRadius() < paddle1.getX() + paddle1.getWidth() &&
                   ball.getY() + ball.getRadius() > paddle1.getY() &&
                   ball.getY() - ball.getRadius() < paddle1.getY() + paddle1.getHeight();
    }

    private float getNewY(){
        return p.random(-ballMaxYSpeed, ballMaxYSpeed);
    }

    private void resetIfGoesOutOfBounds() {
        if(ball.isGoingRight() && ball.getX() - ball.getRadius() > p.width) {
            p1Score++;
            ball.reset("left");
            ballMaxYSpeed = startingBallMaxYSpeed;
            paddle1.reset();
            paddle2.reset();
        }

        else if(!ball.isGoingRight() && ball.getX() + ball.getRadius() < 0) {
            p2Score++;
            ball.reset("right");
            ballMaxYSpeed = startingBallMaxYSpeed;
            paddle1.reset();
            paddle2.reset();
        }
    }

    private void drawScores() {
        p.text(p1Score, 0.2f * p.width, 0.2f * p.height);
        p.text(p2Score, 0.8f * p.width, 0.2f * p.height);
    }

    private void drawCentralLine() {
        p.rect((float)p.width/2, 0, 5, p.height);
    }
}
