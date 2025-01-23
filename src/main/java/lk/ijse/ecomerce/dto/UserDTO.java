package lk.ijse.ecomerce.dto;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int userId;
    private String formatted_user_id;
    private String userName;
    private String password;
    private String email;
    private String role;
    private boolean active;

    public UserDTO(int userId, String userName, String email, boolean active) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.active = active;
    }

    public UserDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserDTO(int userId, String userName, String password, String email, String role, boolean active) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.active = active;
    }

    public UserDTO(String userName, String email, String role, boolean active, int userId) {
        this.userName = userName;
        this.email = email;
        this.role = role;
        this.active = active;
        this.userId = userId;
    }

    public UserDTO(int userId, String userName, String email, String role) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.role = role;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
    public UserDTO(String userName, String password, String email, String role, boolean active) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.active = active;
    }

    public UserDTO(int anInt, String string, String string1, String string2, String string3, int anInt1) {
        this.userId = anInt;
        this.userName = string;
        this.password = string1;
        this.email = string2;
        this.role = string3;
        this.active = anInt1 == 1;
    }
}