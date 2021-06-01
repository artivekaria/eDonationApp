package com.example.nik.fcnc;

/**
 * Created by dc on 22-Jan-18.
 */
public class MaterialHero {
    String ngo_name,material,quantity,des,jdate,email;

    public MaterialHero(String ngo_name, String material, String quantity, String des, String jdate, String email) {
        this.ngo_name = ngo_name;
        this.material = material;
        this.quantity = quantity;
        this.des = des;
        this.jdate = jdate;
        this.email = email;
    }

    public String getNgo_name() {
        return ngo_name;
    }

    public void setNgo_name(String ngo_name) {
        this.ngo_name = ngo_name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getJdate() {
        return jdate;
    }

    public void setJdate(String jdate) {
        this.jdate = jdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
