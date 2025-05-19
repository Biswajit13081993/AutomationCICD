
@tag
Feature: Error Validation
  I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given: I landed on Ecommerce Page
    Given Logged in with username <emails> and password <passWord>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | emails  						| passWord 	 |	
      | Rahul13@gmail.com 	| Biswa@3 	 |	
