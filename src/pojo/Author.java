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
public class Author {
    private String id;
    private String nickname;
    private String name;
    private String birthday;
    private String gender;
    private String city;
    private String country;
    private String background;
    private String description;

    public Author(String id, String nickname, String name, String birthday, String gender, String city, String country, String background, String description) {
        this.id = id;
        this.nickname = nickname;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.city = city;
        this.country = country;
        this.background = background;
        this.description = description;
    }  
    
    
    @Override
    public String toString() {
        return this.nickname;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }    
}
