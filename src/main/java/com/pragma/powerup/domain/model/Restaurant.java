package com.pragma.powerup.domain.model;

public class Restaurant extends ObjectModel {
    private String nit;
    private String address;
    private String telephone;
    private String logoUrl;
    private Long userId;

    public Restaurant(){

    }

    public Restaurant(Long id, String name, String nit, String address, String telephone, String logoUrl, Long userId) {
        super(id, name);
        this.nit = nit;
        this.address = address;
        this.telephone = telephone;
        this.logoUrl = logoUrl;
        this.userId = userId;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
