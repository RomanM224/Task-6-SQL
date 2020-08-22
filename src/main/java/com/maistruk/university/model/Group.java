package com.maistruk.university.model;

public class Group {

    private int id;
    private String name;

    public Group() {
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
    public String toString() {
        return "Group [id=" + id + " name=" + name + "]";
    }

    @Override
    public boolean equals(Object comparedGroup) {
        Group group = (Group) comparedGroup;
        if (group.id != id) {
            return false;
        }
        if (!group.name.equals(name)) {
            return false;
        }
        return true;

    }

}
