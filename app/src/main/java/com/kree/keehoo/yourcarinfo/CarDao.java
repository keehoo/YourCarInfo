package com.kree.keehoo.yourcarinfo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CAR".
*/
public class CarDao extends AbstractDao<Car, Long> {

    public static final String TABLENAME = "CAR";

    /**
     * Properties of entity Car.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Brand = new Property(1, String.class, "brand", false, "BRAND");
        public final static Property Model = new Property(2, String.class, "model", false, "MODEL");
        public final static Property RegNum = new Property(3, String.class, "regNum", false, "REG_NUM");
        public final static Property DateOfInsuranceStart = new Property(4, Long.class, "dateOfInsuranceStart", false, "DATE_OF_INSURANCE_START");
        public final static Property DateOfInsuranceEnd = new Property(5, Long.class, "dateOfInsuranceEnd", false, "DATE_OF_INSURANCE_END");
        public final static Property DateOfTechStart = new Property(6, Long.class, "dateOfTechStart", false, "DATE_OF_TECH_START");
        public final static Property DateofTechEnd = new Property(7, Long.class, "dateofTechEnd", false, "DATEOF_TECH_END");
    };


    public CarDao(DaoConfig config) {
        super(config);
    }
    
    public CarDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CAR\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"BRAND\" TEXT," + // 1: brand
                "\"MODEL\" TEXT," + // 2: model
                "\"REG_NUM\" TEXT," + // 3: regNum
                "\"DATE_OF_INSURANCE_START\" INTEGER," + // 4: dateOfInsuranceStart
                "\"DATE_OF_INSURANCE_END\" INTEGER," + // 5: dateOfInsuranceEnd
                "\"DATE_OF_TECH_START\" INTEGER," + // 6: dateOfTechStart
                "\"DATEOF_TECH_END\" INTEGER);"); // 7: dateofTechEnd
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CAR\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Car entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String brand = entity.getBrand();
        if (brand != null) {
            stmt.bindString(2, brand);
        }
 
        String model = entity.getModel();
        if (model != null) {
            stmt.bindString(3, model);
        }
 
        String regNum = entity.getRegNum();
        if (regNum != null) {
            stmt.bindString(4, regNum);
        }
 
        Long dateOfInsuranceStart = entity.getDateOfInsuranceStart();
        if (dateOfInsuranceStart != null) {
            stmt.bindLong(5, dateOfInsuranceStart);
        }
 
        Long dateOfInsuranceEnd = entity.getDateOfInsuranceEnd();
        if (dateOfInsuranceEnd != null) {
            stmt.bindLong(6, dateOfInsuranceEnd);
        }
 
        Long dateOfTechStart = entity.getDateOfTechStart();
        if (dateOfTechStart != null) {
            stmt.bindLong(7, dateOfTechStart);
        }
 
        Long dateofTechEnd = entity.getDateOfTechEnd();
        if (dateofTechEnd != null) {
            stmt.bindLong(8, dateofTechEnd);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Car readEntity(Cursor cursor, int offset) {
        Car entity = new Car( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // brand
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // model
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // regNum
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4), // dateOfInsuranceStart
            cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5), // dateOfInsuranceEnd
            cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6), // dateOfTechStart
            cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7) // dateofTechEnd
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Car entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setBrand(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setModel(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setRegNum(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setDateOfInsuranceStart(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.setDateOfInsuranceEnd(cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5));
        entity.setDateOfTechStart(cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
        entity.setDateOfTechEnd(cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Car entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Car entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
