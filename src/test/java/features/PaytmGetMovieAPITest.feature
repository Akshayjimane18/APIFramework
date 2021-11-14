Feature: Verify whether fetch movies API

  Scenario Outline: : Verify whether fetch movies API
    Given Upcoming movies api is given
    When User calls "GetUpcomingMovies" API with "GET" http request
    Then Status code should  <Status Code>
    And Movie release date should not be greater than today's date
    And Movie posted URL should only have ".jpg" format
    And Paytm movie code is unique
    And No movie code should have more than 1 language format
    Then Write down name of the all the movies whose content available is 0

    Examples:
      |Status Code|
      |200|