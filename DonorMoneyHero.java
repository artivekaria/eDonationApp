package com.example.nik.fcnc;

import android.content.Context;

/**
 * Created by dc on 23-Jan-18.
 */
public class DonorMoneyHero {
    String ngo_name,donor_name,donor_email,payment_mode,donet_money,number,address;

    public DonorMoneyHero(String ngo_name, String donor_name, String donor_email, String payment_mode, String donet_money, String number, String address) {
        this.ngo_name = ngo_name;
        this.donor_name = donor_name;
        this.donor_email = donor_email;
        this.payment_mode = payment_mode;
        this.donet_money = donet_money;
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

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }

    public String getDonet_money() {
        return donet_money;
    }

    public void setDonet_money(String donet_money) {
        this.donet_money = donet_money;
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
