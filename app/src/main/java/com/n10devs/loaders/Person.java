package com.n10devs.loaders;

import android.provider.BaseColumns;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = Person.TABLE, id = BaseColumns._ID)
public class Person extends Model{

    public static final String TABLE = "persons";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE = "age";

    @Column(name = COLUMN_NAME)
    private String name;

    @Column(name = COLUMN_AGE)
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
