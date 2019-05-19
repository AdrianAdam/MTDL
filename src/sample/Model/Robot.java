/*
 * Developed by Adrian Adam on 5/19/19 6:39 PM.
 * Last modified 5/16/19 8:32 AM.
 * Copyright (c) 2019. All rights reserved.
 */

package sample.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Robot {
    private int robot_id;
    private int user_id;
    private String name;
    private String state;
    private int coordX;
    private int coordY;
    private String type;
    private String icon;
    private String image;
    private String connectivity;
}
