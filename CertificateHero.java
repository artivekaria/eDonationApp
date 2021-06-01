package com.example.nik.fcnc;

/**
 * Created by Admin on 14-Apr-18.
 */

public class CertificateHero
{

    String ngo_name,donor_name,donet_money,text,address;

    public CertificateHero() {
        this.ngo_name = ngo_name;
        this.donor_name = donor_name;
        this.donet_money = donet_money;
        this.text = text;
        this.address = address;
    }

    public String getNgo_name() {
        return ngo_name;
    }

    public void setNgo_name(String ngo_name) {
        this.ngo_name = ngo_name;
    }

    public String getDonor_name() {
        return donor_name;
    }

    public void setDonor_name(String donor_name) {
        this.donor_name = donor_name;
    }

    public String getDonet_money() {
        return donet_money;
    }

    public void setDonet_money(String donet_money) {
        this.donet_money = donet_money;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
