package com.kree.keehoo.yourcarinfo.DaoGeneratedFiles;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "CAR".
 */
public class Car {

    private Long id;
    private String brand;
    private String model;
    private String regNum;
    private Long dateOfInsuranceStart;
    private Long dateOfInsuranceEnd;
    private Long dateOfTechStart;
    private Long dateOfTechEnd;

    public Car() {
    }

    public Car(Long id) {
        this.id = id;
    }

    public Car(Long id, String brand, String model, String regNum, Long dateOfInsuranceStart, Long dateOfInsuranceEnd, Long dateOfTechStart, Long dateOfTechEnd) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.regNum = regNum;
        this.dateOfInsuranceStart = dateOfInsuranceStart;
        this.dateOfInsuranceEnd = dateOfInsuranceEnd;
        this.dateOfTechStart = dateOfTechStart;
        this.dateOfTechEnd = dateOfTechEnd;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public Long getDateOfInsuranceStart() {
        return dateOfInsuranceStart;
    }

    public void setDateOfInsuranceStart(Long dateOfInsuranceStart) {
        this.dateOfInsuranceStart = dateOfInsuranceStart;
    }

    public Long getDateOfInsuranceEnd() {
        return dateOfInsuranceEnd;
    }

    public void setDateOfInsuranceEnd(Long dateOfInsuranceEnd) {
        this.dateOfInsuranceEnd = dateOfInsuranceEnd;
    }

    public Long getDateOfTechStart() {
        return dateOfTechStart;
    }

    public void setDateOfTechStart(Long dateOfTechStart) {
        this.dateOfTechStart = dateOfTechStart;
    }

    public Long getDateOfTechEnd() {
        return dateOfTechEnd;
    }

    public void setDateOfTechEnd(Long dateOfTechEnd) {
        this.dateOfTechEnd = dateOfTechEnd;
    }

}
