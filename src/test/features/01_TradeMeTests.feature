#Author: Chandra Srivastava
#Date: 29/11/2022
Feature: to Verify search results on TradeMe

@tradeMe_Demo
#UI test 1
  Scenario: Verify number of named car makes available in Make drop down
    Given User navigates to tradeMe Motor Search Page
    Then Number of vehicles available under Make dropdown are validated against value from property file

@tradeMe_Demo
#UI test 2
  Scenario Outline: Verify number of cars returned using search criteria
    Given User navigates to tradeMe Motor Search Page
    Then User searches '<VehicleMake>' under make dropdown and saves the result

    Examples:
      | VehicleMake   |
      | Ferrari       |
      | BMW           |
      | Mazda         |
      | Honda         |

@tradeMe_Demo
#API Test 1
  Scenario: Get total number of cars brands available from API response
    Given I go to the Cars Category API from TradeMe sandbox endpoint
    When I get list of Cars Brands from API response
    Then Total Number of named brands of used cars available in the TradeMe UsedCars category matches to property file