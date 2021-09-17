package org.example;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: pguo
 * @Date: 2021/9/17 16:09
 */
public class Company {

    private int companyId;
    private String companyName;
    private String companyDomain;

    private Set<User> employees;
    private Set<User> customers;

    public Company(int companyId, String companyName, String companyDomain) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyDomain = companyDomain;
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getCompanyDomain() {
        return companyDomain;
    }


    public void separateUsersByType(List<User> users){
        employees = users.stream().filter(user -> user.getUserType().equals(User.UserType.EMPLOYEE)).collect(Collectors.toSet());
        customers = users.stream().filter(user -> user.getUserType().equals(User.UserType.CUSTOMER)).collect(Collectors.toSet());
    }


    public int trackNumberOfEmployees(User user){

        moveUserIfNeeded(user);

       return employees.size();
    }

    private void moveUserIfNeeded(User user){

        if(user.getUserType().equals(User.UserType.EMPLOYEE)) {
            if(employees.contains(user)) {
                employees.add(user);
            } else {
                employees.add(user);
                customers.remove(user);
            }
        } else  {
            if(employees.contains(user)) {
                customers.add(user);
                employees.remove(user);
            } else {
                customers.add(user);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Company company = (Company) o;

        return companyId == company.companyId;
    }

    @Override
    public int hashCode() {
        return companyId;
    }

}
