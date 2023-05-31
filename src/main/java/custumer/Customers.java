package custumer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customers {

    public int id;
    public String email;
    public String order;
    public Services services;
    public String dateSubscribe;
    public String dateResubscribe;
    public String availability;
    public String createDate;
}
