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
public class PublishingCompany {
    private String id;
    private String name;
    private String phone;
    private String address;
    private String desc;

    public PublishingCompany(String id, String name, String phone, String address, String desc) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.desc = desc;
    }
    @Override
    public String toString() {
        return this.name;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    
}
