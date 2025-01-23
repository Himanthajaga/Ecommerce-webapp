package lk.ijse.ecomerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private int user_id;
    private String formatted_user_id;
    private String user_name;
    private String password;
    private String email;
    private String role;
    private int active;

    public User(String username, String password, String email, String role, int active) {
        this.user_name = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.active = active;
    }

    public User(int userId, String userName, String password, String email, String role, boolean active) {
        this.user_id = userId;
        this.user_name = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.active = active ? 1 : 0;

    }

    public User(int userId, String username, String password, String email, String role, int active) {
        this.user_id = userId;
        this.user_name = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.active = active;
    }
}
