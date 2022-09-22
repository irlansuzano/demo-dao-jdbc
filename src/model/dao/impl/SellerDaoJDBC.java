package model.dao.impl;

import db.DB;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller seller) {

    }

    @Override
    public void update(Seller seller) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    /**
     * Monta o objeto seller
     * @param id
     * @return Seller
     */
    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT seller.*, department.Name as DepName " +
                    "From seller INNER JOIN department" +
                    "ON seller.DepartmentId = department.Id" +
                    "WHERE seller.Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()){
                Department dp = new Department();
                Seller seller = new Seller();
                dp.setId(rs.getInt("DepartmentId"));
                dp.setName(rs.getString("DepName"));
                seller.setId(rs.getInt("Id"));
                seller.setName(rs.getString("Name"));
                seller.setEmail(rs.getString("Email"));
                seller.setBaseSalary(rs.getDouble("BaseSalary"));
                seller.setBirthDate(rs.getDate("BirthDate"));
                seller.setDepartment(dp);
                return seller;
            }
            return null;
        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }


        return null;
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }
}
