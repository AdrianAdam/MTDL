/*
 * Developed by Adrian Adam on 5/19/19 6:39 PM.
 * Last modified 5/8/19 2:21 PM.
 * Copyright (c) 2019. All rights reserved.
 */

package sample.Model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private int id;
    private String username;
    private String role;
    private String password;
    private String email;
    private int noRobots;
}
