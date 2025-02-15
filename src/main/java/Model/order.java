package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class order {
    private String orderid;
    private String employeeId;
    private String totalcost;
    private String orderdate;
}
