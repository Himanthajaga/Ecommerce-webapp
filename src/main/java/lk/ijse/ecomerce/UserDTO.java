package lk.ijse.ecomerce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int user_id;
    private String user_name;
    private String password;
    private String email;
   private String role;
    private int active;

    public UserDTO(int userId, String username, String email, String active) {
    }
}
