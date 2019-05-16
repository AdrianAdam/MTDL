package sample.Movement;

import sample.Model.Robot;

public class MovementRight implements Movement {

    @Override
    public void move(Robot robot, int movementValue) {
        robot.setCoordX(robot.getCoordX() + movementValue);
    }
}
