Feature: Login into Orange HRM WebPage
  As an employee in Orange i should be able to,
  login into Orange HRM.

  Background:
    Given i am at the OrangeHRM Login Page

    Scenario: Login with valid credentials
      Given i have entered the username and password
      When i click on the login button
      Then i will be logged in successfully


