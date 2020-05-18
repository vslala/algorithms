package com.bma.algorithms.priorityqueues;

import com.bma.algorithms.stdlib.StdDraw;

import java.awt.*;

public class EventDrivenSimulation {

    public EventDrivenSimulation(int numOfBalls) {
        new BouncingBalls().simulate(numOfBalls);
    }

    public  static class Ball {
        private double rx, ry; // position
        private double vx, vy; // velocity
        private final double radius = .01;

        public void move(double dt) {
            if ((rx + vx*dt < radius) || (rx + vx * dt > 1.0 - radius)) {
                vx = -vx;
            }
            if ((ry + vy * dt < radius) || (ry + vy * dt > 1.0 -radius)) {
                vy = -vy;
            }
            rx = rx + vx * dt;
            ry = ry + vy * dt;
        }

        public void draw() {
            StdDraw.filledCircle(rx, ry, radius);
        }
    }

    public static class BouncingBalls {
        public void simulate(int numOfBalls) {
            Ball[] balls = new Ball[numOfBalls];

            for (int i=0; i < numOfBalls; i++) {
                balls[i] = new Ball();
            }

            while (true) {
                StdDraw.clear(Color.WHITE);
                for (int i=0; i < numOfBalls; i++) {
                    balls[i].move(0.5);
                    balls[i].draw();
                }
                StdDraw.show(50);
            }
        }
    }

    public static void main(String[] args) {
        EventDrivenSimulation simulation = new EventDrivenSimulation(100);
    }
}
