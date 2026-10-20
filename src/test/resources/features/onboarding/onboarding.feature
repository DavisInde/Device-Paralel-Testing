@onboarding_page

Feature: Onboarding

    Background:
        Given User is on onboarding page

    @onboarding @magic
    Scenario: Disable Magic Page
        When user tap alfamart logo 5 times
        Then User is on onboarding page

    @onboarding
    Scenario: Verify promo button is visible
        Then promo button should be visible

    @onboarding
    Scenario: Verify store button is visible
        Then store button should be visible

    @onboarding
    Scenario: Search store by name
        When user searches for a store by name
        Then matching store should be displayed

    @onboarding
    Scenario: Search store by address
        When user searches for a store by address
        Then matching store should be displayed
