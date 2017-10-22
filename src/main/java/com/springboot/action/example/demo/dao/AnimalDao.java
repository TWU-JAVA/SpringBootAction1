package com.springboot.action.example.demo.dao;

import com.springboot.action.example.demo.bean.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AnimalDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Animal findObjectByName(String name) {
        try {
            Animal animal = new Animal();
            String sql = "select * from animal where name =?";
            Object args[] = new Object[]{name};

            jdbcTemplate.query(sql,args, new RowCallbackHandler() {
                        public void processRow(ResultSet rs) throws SQLException {
                            animal.setName(rs.getString("name"));
                            animal.setAge(rs.getInt("age"));
                            animal.setLegNum(rs.getInt("legNum"));
                        }
                    }
            );
            return animal;
        }catch (Exception e){
            System.out.println("MYSQL ERROR:" + e.getMessage());
        }
        return null;
    }

    public Boolean create(Animal animal) {
        Boolean isCreated = false;
        try {
            System.out.println(jdbcTemplate.update("insert into animal(name,age,legNum) values(?,?,?)",
                    new Object[]{animal.getName(), animal.getAge(), animal.getLegNum()}));
            isCreated = true;

        } catch (Exception e) {
            System.out.print("MYSQL ERROR:" + e.getMessage());
        }
        return isCreated;
    }

    public Boolean delete(String name) {
        Boolean isDeleted = false;
        try {
            jdbcTemplate.update(
                    "delete from animal where name = ?",
                    name);
            isDeleted = true;
        }catch (Exception e){
            System.out.print("MYSQL ERROR:" + e.getMessage());
        }
        return isDeleted;
    }

    public Boolean update(Animal animal){
        Boolean isUpdated = false;
        try {
            jdbcTemplate.update(
                    "update animal set legNum = ? where name = ?",
                    new Object[]{animal.getLegNum(), animal.getName()});
            isUpdated =true;
        }catch (Exception e){
            System.out.print("MYSQL ERROR:" + e.getMessage());
        }
        return isUpdated;
    }
}
