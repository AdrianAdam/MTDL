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
