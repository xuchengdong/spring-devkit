package com.df.domain;

/**
 * @author xuchengdong@qbao.com on 2017/8/23.
 */
public class VehicleDetails {
    private String make;
    private String model;

    public VehicleDetails() {
    }

    public VehicleDetails(String make, String model) {
        this.make = make;
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "VehicleDetails{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VehicleDetails that = (VehicleDetails) o;

        if (make != null ? !make.equals(that.make) : that.make != null) return false;
        return model != null ? model.equals(that.model) : that.model == null;
    }

    @Override
    public int hashCode() {
        int result = make != null ? make.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        return result;
    }
}
