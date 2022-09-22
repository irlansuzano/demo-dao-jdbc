package model.dao;

import db.DB;
import model.dao.impl.SellerDaoJDBC;

import java.sql.Connection;

public class DaoFactory {

    public static SellerDao createSellerDao(){              //macete para não expor a implementação
        return new SellerDaoJDBC(DB.getConnection());
    }
}
