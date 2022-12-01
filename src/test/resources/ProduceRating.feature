Feature:
  Scenario: Service should be able to produce a rating event to kafka
    Given a rating of 8 out of 10 is rated by a user
    When a post request is sent with rating in the body
    Then a kafka event is produced
