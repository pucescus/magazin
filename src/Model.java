import lombok.Data;

@Data
public class Товар {
    private int id;
    private String название;
    private String описание;
    private double цена;
    private int количествоВОстатке;
    private int количествоВКорзине;
}