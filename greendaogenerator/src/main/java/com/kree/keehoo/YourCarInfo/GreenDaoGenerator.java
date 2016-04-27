package com.kree.keehoo.YourCarInfo;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class GreenDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "com.kree.keehoo.yourcarinfo");

        Entity car = schema.addEntity("Car");
        car.addIdProperty();
        car.addStringProperty("brand");
        car.addStringProperty("model");
        car.addStringProperty("regNum");
        car.addLongProperty("dateOfInsuranceStart");
        car.addLongProperty("dateOfInsuranceEnd");
        car.addLongProperty("dateOfTechStart");
        car.addLongProperty("dateofTechEnd");


        new DaoGenerator().generateAll(schema, "../app/src/main/java");
    }
}
