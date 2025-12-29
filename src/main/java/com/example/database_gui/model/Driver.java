package com.example.database_gui.model;

public class Driver extends Person
{

    private String licenseNum;

    public Driver(String id, String name, String licenseNum)
    {
        super(id, name);
        this.licenseNum = licenseNum;
    }

    public Driver(String id, String name)
    {
        super(id, name);
    }

    public String getLicenseNum()
    {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum)
    {
        this.licenseNum = licenseNum;
    }

    @Override
    public String toString()
    {
        return "Driver{" + "id='" + getId() + '\'' + ", name='" + getName() + '\'' +
                ", licenseNum='" + licenseNum + '\'' + '}';
    }
}