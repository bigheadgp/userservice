package org.example;

import static org.example.User.UserType;

/** @Author: pguo
 * @Date: 2021/9/17 16:02 */
public class UserService {

  // All users are stored in a database.

  // changing a user’s email
  public void changeEmail(int userId, String newEmail) {

    CompanyDao companyDao = new CompanyDao();
    User user = companyDao.findUserById(userId);

    Company company = companyDao.findCompanyById(user.getCompanyId());
    String companyDomain = company.getCompanyDomain();

    companyDao.changeEmail(user, newEmail);

    // If the user’s email belongs to the company’s domain, that user is marked as an
    // employee. Otherwise, they are treated as a customer
    decisionUserType(newEmail, user, companyDomain);

    // The system must track the number of employees in the company. If the user’s
    // type changes from employee to customer, or vice versa, this number must
    // change, too
    int numberOfEmployees = company.trackNumberOfEmployees(user);

    // When the email changes, the system must notify external systems by sending a
    // message to a message bus
    MessageBus messageBus = new MessageBus();
    String message = "Email changed successfully, number of employees is " + numberOfEmployees;
    messageBus.sendMessageToMessageBus(message);

  }

  public void decisionUserType(String newEmail, User user, String companyDomain) {
    if (isEmailWithCompanyDomain(newEmail, companyDomain)) user.setUserType(UserType.EMPLOYEE);
    else user.setUserType(UserType.CUSTOMER);
  }

  private boolean isEmailWithCompanyDomain(String newEmail, String companyDomain) {
    String domainOfNewEmail = newEmail.substring(newEmail.indexOf('@') + 1);
    return companyDomain.equalsIgnoreCase(domainOfNewEmail);
  }

}
