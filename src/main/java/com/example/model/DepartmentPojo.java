package com.example.model;

import com.example.util.constants.Regex;
import com.example.util.validation.annotaion.uniqueness.UniqueName;
import com.example.util.validation.annotaion.uniqueness.UniquePhone;
import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;
import java.util.Objects;

@UniqueName
@UniquePhone
public class DepartmentPojo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotEmpty(message = "Enter name.")
    @NotNull(message = "Enter name.")
    private String name;
    @NotEmpty(message = "Enter phone number.")
    @NotNull(message = "Enter phone number.")
    @MatchPattern(pattern = Regex.PHONE_REGEX,
            message = "Phone number is not valid.")
    private String number;

    public DepartmentPojo() {
    }

    public DepartmentPojo(Long id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public DepartmentPojo(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        DepartmentPojo that = (DepartmentPojo) object;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, number);
    }

    @Override
    public String toString() {
        return "DepartmentPojo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
