package com.example.hrm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Delete {
    @SerializedName("id")
    @Expose
    private Long id;

    public Long getId() {
        return id;
    }

    public void setName(Long id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\''+
                '}';
    }
}
