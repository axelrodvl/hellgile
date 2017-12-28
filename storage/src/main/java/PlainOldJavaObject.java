import lombok.Getter;
import lombok.Setter;

/**
 * Created by Vadim Axelrod (vadim@axelrod.co) on 28.12.2017.
 */

@Getter
@Setter
public class PlainOldJavaObject {
    private String name;
    private Integer price;

    @Override
    public String toString() {
        return "Name: " + name + ", price: " + price;
    }
}
