package lk.ijse.ecomerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private int user_id;
    private String user_name;
    private String password;
    private String email;
    private String role;
    private int active;

    public User(String username, String password, String email, String role, int active) {
    }
}
