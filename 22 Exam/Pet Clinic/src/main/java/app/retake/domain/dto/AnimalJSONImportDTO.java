package app.retake.domain.dto;

import app.retake.domain.models.Passport;
import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AnimalJSONImportDTO implements Serializable {

    @Expose
    @NotNull
    @Length(min = 3, max = 20)
    private String name;

    @Expose
    @NotNull
    @Length(min = 3, max = 20)
    private String type;

    @Expose
    @Min(1)
    private int age;

    @Expose
    private PassportJSONImportDTO passport;

    public AnimalJSONImportDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PassportJSONImportDTO getPassport() {
        return passport;
    }

    public void setPassport(PassportJSONImportDTO passport) {
        this.passport = passport;
    }
}
