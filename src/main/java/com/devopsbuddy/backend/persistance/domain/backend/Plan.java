package com.devopsbuddy.backend.persistance.domain.backend;

import com.devopsbuddy.enums.PlansEnum;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Plan implements Serializable {

    /** The Serial Version UID for Serializable classes. **/
    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    private String name;

    /** Default constructor **/
    public Plan() {

    }

    public  Plan(PlansEnum plansEnum) {
        id = plansEnum.getId();
        name = plansEnum.getPlanName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return id == plan.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
