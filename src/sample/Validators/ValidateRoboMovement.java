/*
 * Developed by Adrian Adam on 5/19/19 6:40 PM.
 * Last modified 5/19/19 6:27 PM.
 * Copyright (c) 2019. All rights reserved.
 */

package sample.Validators;

public class ValidateRoboMovement {
    public ValidateRoboMovement() {
    }

    public boolean checkMovement(int coordX, int coordY, int movementValue, String direction) {
        boolean check = true;

        switch (direction) {
            case "up":
                if (coordY - movementValue < 200) {
                    check = false;
                }
                break;
            case "down":
                if (coordY + movementValue > 670) {
                    check = false;
                }
                break;
            case "left":
                if (coordX - movementValue < 500) {
                    check = false;
                }
                break;
            case "right":
                if (coordX + movementValue > 1250) {
                    check = false;
                }
                break;
            default:
                break;
        }

        return check;
    }
}
