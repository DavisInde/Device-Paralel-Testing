@login_apo

Feature: Login

  Background:
    Given Login Test

  @login
  Scenario: Verify user can login
    Given user on Splash Screen
    And user tap asal asalan
    Then user should see