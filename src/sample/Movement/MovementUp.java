package sample.Movement;

import sample.Model.Robot;

public class MovementUp implements Movement {

    @Override
    public void move(Robot robot, int movementValue) {
        robot.setCoordY(robot.getCoordY() - movementValue);
    }
}
