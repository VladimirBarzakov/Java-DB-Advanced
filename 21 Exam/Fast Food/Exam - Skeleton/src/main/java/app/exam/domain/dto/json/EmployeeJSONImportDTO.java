package app.exam.domain.dto.json;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class EmployeeJSONImportDTO  {

    @Expose
    @NotNull
    @Length(min=3, max = 30)
    private String name;

    @Expose
    @NotNull
    @Min(15)
    @Max(80)
    private int age;

    @Expose
    @NotNull
    @Length(min=3, max = 30)
    private String position;

    public EmployeeJSONImportDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
