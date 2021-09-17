package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: pguo
 * @Date: 2021/9/17 16:03
 */
public class Database {

    private static Database database;

    private static List<Company> companies = new ArrayList<>(10);
    private static List<User> users = new ArrayList<>(20);

    private Database(){
        initDatabase();
    }

    public static Database getInstance(){
        if(database == null) database = new Database();
       return database;
    }

    private static void initDatabase(){

        Company company1 = new Company(1, "IBM", "ibm.com");
        Company company2 = new Company(2, "Alibaba", "alibaba.com");

        User user1 = new User(1,"johnson@ibm.com", User.UserType.EMPLOYEE, 1);
        User user2= new User(2,"micky@tesla.com", User.UserType.CUSTOMER, 1);
        User user3= new User(3,"tommy@google.com", User.UserType.CUSTOMER, 1);

        User user4 = new User(4,"lujun@alibaba.com", User.UserType.EMPLOYEE, 2);
        User user5= new User(5,"xiaoqiang@tesla.com", User.UserType.CUSTOMER, 2);
        User user6= new User(6,"nanyu@google.com", User.UserType.CUSTOMER, 2);

        companies.add(company1);
        companies.add(company2);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);

    }


    public Company selectCompanyByCompanyId(final int companyId) {
        return companies.stream().filter(company -> company.getCompanyId()== companyId).findAny().orElse(null);
    }

    public List<User> selectAllUsersByCompanyId(int companyId) {
        return users.stream().filter(user -> user.getCompanyId()==companyId).collect(Collectors.toList());
    }


   public User selectUserByUserId(int userId){
        return users.stream().filter(user -> user.getUserId()==userId).findAny().orElse(null);
   }

   public void saveUser(User user) {

        if(users.contains(user)) {
            users.set(users.indexOf(user), user);
        } else {
            users.add(user);
        }

   }

}
