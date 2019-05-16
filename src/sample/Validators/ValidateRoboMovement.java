package sample.Validators;

public class ValidateRoboMovement {
    public ValidateRoboMovement() {
    }

    public boolean checkMovement(int coordX, int coordY, int movementValue, String direction) {
        boolean check = true;

        switch (direction) {
            case "up":
                if (coordY - movementValue < 85) {
                    check = false;
                }
                break;
            case "down":
                if (coordY + movementValue > 585) {
                    check = false;
                }
                break;
            case "left":
                if (coordX - movementValue < 567) {
                    check = false;
                }
                break;
            case "right":
                if (coordX + movementValue > 1367) {
                    check = false;
                }
                break;
            default:
                break;
        }

        return check;
    }
}
