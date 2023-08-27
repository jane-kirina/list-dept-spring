package com.example.model;

import com.example.util.validation.annotaion.ExistDept;
import com.example.util.validation.annotaion.ValidAge;
import com.example.util.validation.annotaion.ValidYearsWorking;
import com.example.util.validation.annotaion.uniqueness.UniqueEmail;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNegative;
import net.sf.oval.constraint.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@UniqueEmail
public class EmployeePojo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotEmpty(message = "Enter name.")
    @NotNull(message = "Enter name.")
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotEmpty(message = "Please enter a valid date.")
    @NotNull(message = "Please enter a valid date.")
    @ValidAge
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthDate;
    @NotEmpty(message = "Enter years of work.")
    @NotNull(message = "Enter years of work.")
    @ValidYearsWorking
    @NotNegative(message = "The number of years of work not validated.")
    private Integer yearsWorking;
    @NotEmpty(message = "Enter email.")
    @NotNull(message = "Enter email.")
    @Email(message = "Please enter a valid mail format.")
    private String email;
    @NotEmpty(message = "Enter id of department.")
    @NotNull(message = "Enter id of department.")
    @ExistDept
    private DepartmentPojo department;

    public EmployeePojo() {
    }

    public EmployeePojo(DepartmentPojo department) {
        this.department = department;
    }

    public EmployeePojo(Long id, String name, LocalDate birthDate, Integer yearsWorking, String email,
                        DepartmentPojo department) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.yearsWorking = yearsWorking;
        this.email = email;
        this.department = department;
    }

    public EmployeePojo(String name, LocalDate birthDate, Integer yearsWorking, String email,
                        DepartmentPojo department) {
        this.name = name;
        this.birthDate = birthDate;
        this.yearsWorking = yearsWorking;
        this.email = email;
        this.department = department;
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getYearsWorking() {
        return yearsWorking;
    }

    public void setYearsWorking(Integer yearsWorking) {
        this.yearsWorking = yearsWorking;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DepartmentPojo getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentPojo department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        EmployeePojo that = (EmployeePojo) object;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(birthDate, that.birthDate)
                && Objects.equals(yearsWorking, that.yearsWorking)
                && Objects.equals(email, that.email)
                && Objects.equals(department, that.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthDate, yearsWorking, email, department);
    }

    @Override
    public String toString() {
        return "EmployeePojo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", yearsWorking=" + yearsWorking +
                ", email='" + email + '\'' +
                ", department=" + department +
                '}';
    }
}
