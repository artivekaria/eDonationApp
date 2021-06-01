package com.example.nik.fcnc;

/**
 * Created by dc on 23-Jan-18.
 */
public class DonorMaterialHero {
    String ngo_name,donor_name,donor_email,material,donet_quantity,number,address;


    public DonorMaterialHero(String ngo_name, String donor_name, String donor_email, String material, String donet_quantity, String number, String address) {
        this.ngo_name = ngo_name;
        this.donor_name = donor_name;
        this.donor_email = donor_email;
        this.material = material;
        this.donet_quantity = donet_quantity;
        this.number = number;
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

    public String getDonor_email() {
        return donor_email;
    }

    public void setDonor_email(String donor_email) {
        this.donor_email = donor_email;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDonet_quantity() {
        return donet_quantity;
    }

    public void setDonet_quantity(String donet_quantity) {
        this.donet_quantity = donet_quantity;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
