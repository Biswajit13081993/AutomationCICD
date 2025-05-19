
@tag
Feature: Purchase order from Ecommerce site
  I want to use this template for my feature file
	
	Background:
	Given: I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of submitting the order
    Given Logged in with username <emails> and password <passWord>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "Thankyou for the order." message is displayed on ConfiramationPage

    Examples: 
      | emails  						| passWord 	 |	productName 		|
      | Rahul13@gmail.com 	| Biswa@13 	 |	ADIDAS ORIGINAL	|
      			 		   		
