package org.example;

import java.util.List;

/**
 * @Author: pguo
 * @Date: 2021/9/17 17:00
 */
public class CompanyDao {

    private static Database database = Database.getInstance();

    public Company findCompanyById(int companyId){

        Company company = database.selectCompanyByCompanyId(companyId);

        company.separateUsersByType(findUsersByCompanyId(companyId));

        return company;
    }


    private List<User> findUsersByCompanyId(int companyId){
        return database.selectAllUsersByCompanyId(companyId);
    }


    public User findUserById(int userId) {
        return database.selectUserByUserId(userId);
    }

    public void saveUser(User user) {
        database.saveUser(user);
    }


}
