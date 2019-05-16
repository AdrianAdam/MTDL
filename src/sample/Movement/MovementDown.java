package sample.Movement;

import sample.Model.Robot;

public class MovementDown implements Movement {

    @Override
    public void move(Robot robot, int movementValue) {
        robot.setCoordY(robot.getCoordY() + movementValue);
    }
}
