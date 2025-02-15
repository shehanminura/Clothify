package Model;

import lombok.*;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor

public class employee {
    private String employeeid;
    private String userid;
    private String name;
    private String company;
    private String email;
    private String password;
}
