package sample.Movement;

import sample.Model.Robot;

public class MovementLeft implements Movement {

    @Override
    public void move(Robot robot, int movementValue) {
        robot.setCoordX(robot.getCoordX() - movementValue);
    }
}
