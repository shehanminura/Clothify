package Model;

import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class products {
    private String ProductID;
    private String Name;
    private String Category;
    private String Size;
    private Double Price;
    private int Quantity;
    private String SupplierID;
    private String ImageURL;


}
