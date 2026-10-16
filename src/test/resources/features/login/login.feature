@login_apo

Feature: Login

  Background:
    Given Login Test

  @login
  Scenario: Verify user can login
    Given user on Splash Screen
    And user tap asal asalan
    Then user should see

  @login_invalid_password
  Scenario: Verify user cannot login with wrong password
    Given User is on onboarding page
    When user enters identity number
    And user taps login button on onboarding page
    Then login page should be visible
    When user enters password "WrongPass123"
    And user taps login button on login page
    Then incorrect password message should be visible