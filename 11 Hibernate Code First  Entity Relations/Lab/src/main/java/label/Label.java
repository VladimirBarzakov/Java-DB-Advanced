package label;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public interface Label extends Serializable {
    int getId();

    void setId(int id);

    String getTitle();

    void setTitle(String title);

    String getSubtitle();

    void setSubtitle(String title);
}
