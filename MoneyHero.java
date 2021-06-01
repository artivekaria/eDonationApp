package com.example.nik.fcnc;

/**
 * Created by dc on 22-Jan-18.
 */
public class MoneyHero {
    String ngo_name, money, des, jdate, email;

    public MoneyHero(String ngo_name, String money, String des, String jdate, String email) {
        this.ngo_name = ngo_name;
        this.money = money;
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

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
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
