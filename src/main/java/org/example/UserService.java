package org.example;

import static org.example.User.UserType;

/** @Author: pguo
 * @Date: 2021/9/17 16:02 */
public class UserService {

  // All users are stored in a database.

  // changing a user’s email
  public void changeEmail(int userId, String newEmail) {

    CompanyDao companyDao = new CompanyDao();
    User previousUser = companyDao.findUserById(userId);

    Company company = companyDao.findCompanyById(previousUser.getCompanyId());
    String companyDomain = company.getCompanyDomain();

    User emailChangedUser = previousUser.clone();

    emailChangedUser.changeEmail(newEmail);
    companyDao.saveUser(emailChangedUser);
    // If the user’s email belongs to the company’s domain, that user is marked as an
    // employee. Otherwise, they are treated as a customer
    decisionUserType(newEmail, emailChangedUser, companyDomain);

    // The system must track the number of employees in the company. If the user’s
    // type changes from employee to customer, or vice versa, this number must
    // change, too
    company.trackNumberOfEmployees(emailChangedUser);

    // When the email changes, the system must notify external systems by sending a
    // message to a message bus
    String message = null;
    sendMessageToMessageBus(message);

  }

  public void decisionUserType(String newEmail, User user, String companyDomain) {
    if (isEmailWithCompanyDomain(newEmail, companyDomain)) user.setUserType(UserType.EMPLOYEE);
    else user.setUserType(UserType.CUSTOMER);
  }

  private boolean isEmailWithCompanyDomain(String newEmail, String companyDomain) {
    String domainOfNewEmail = newEmail.substring(newEmail.indexOf('@'));
    return companyDomain.equalsIgnoreCase(domainOfNewEmail);
  }

  public void sendMessageToMessageBus(String message) {}

}
