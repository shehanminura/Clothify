package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Data
@NoArgsConstructor

public class CartTM {
    private String itemid;
    private String employeeid;
    private String name;
    private int qtyOnHande;
    private Double unitPrice;
    private Double total;
}
