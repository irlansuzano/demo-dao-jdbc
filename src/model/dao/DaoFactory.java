package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {

    public static SellerDao createSellerDao(){              //macete para não expor a implementação
        return new SellerDaoJDBC();
    }
}
