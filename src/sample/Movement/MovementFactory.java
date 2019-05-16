package sample.Movement;

public class MovementFactory {

    public Movement getMovement(String direction) {
        if (direction == null) {
            return null;
        }

        if (direction.equals("up")) {
            return new MovementUp();
        } else if (direction.equals("down")) {
            return new MovementDown();
        } else if (direction.equals("left")) {
            return new MovementLeft();
        } else if (direction.equals("right")) {
            return new MovementRight();
        }

        return null;
    }
}
