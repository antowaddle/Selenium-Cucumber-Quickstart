@feature-a @regression
Feature: Feature number 1
  As a brilliant and caring individual
  I want to be able to adopt a puppy
  So that I can do a lil' good in the world


  Scenario: User adopts a puppy 002
    Given the puppy adoption page has been loaded
    When brook is adopted with the following options:
    | option    |
    | collar    |
    | toy       |
    | carrier   |
    | vet visit |
    Then the price of adoption is 312.87
