package org.example;

/**
 * @Author: pguo
 * @Date: 2021/9/17 16:03
 */
public class User{

    private int userId;
    private String email;
    private UserType userType;

    public static enum UserType {
        EMPLOYEE, CUSTOMER
    }


    private int companyId;

    public User(int userId, String email, UserType userType, int companyId) {
        this.userId = userId;
        this.email = email;
        this.userType = userType;
        this.companyId = companyId;
    }

    public User(User user){
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.companyId = user.getCompanyId();
        this.userType = user.getUserType();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }


//    public User clone(){
//        return  new User(this.userId, this.email, this.userType, this.companyId);
//    }

//
//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return userId == user.userId;
    }

    @Override
    public int hashCode() {
        return userId;
    }

}
