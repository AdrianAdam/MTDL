/*
 * Developed by Adrian Adam on 5/19/19 6:39 PM.
 * Last modified 5/16/19 10:09 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package sample.Movement;

import sample.Model.Robot;

public interface Movement {
    void move(Robot robot, int movementValue);
}
