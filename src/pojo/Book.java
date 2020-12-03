/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author ngtro_f237v2a
 */
public class Book {
    private String id;
    private String name;
    private String author_id;
    private String category_id;
    private String pc_id;
    private String pc_year;
    private String entry_date;
    private String location;
    private String desc;

    public Book(String id, String name, String author_id, String category_id, String pc_id, String pc_year, String entry_date, String location, String desc) {
        this.id = id;
        this.name = name;
        this.author_id = author_id;
        this.category_id = category_id;
        this.pc_id = pc_id;
        this.pc_year = pc_year;
        this.entry_date = entry_date;
        this.location = location;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getPc_id() {
        return pc_id;
    }

    public void setPc_id(String pc_id) {
        this.pc_id = pc_id;
    }

    public String getPc_year() {
        return pc_year;
    }

    public void setPc_year(String pc_year) {
        this.pc_year = pc_year;
    }

    public String getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(String entry_date) {
        this.entry_date = entry_date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
