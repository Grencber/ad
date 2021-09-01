Endpoints----------

/menu -> returns all the available toppings and drinks list
/cart -> returns all the drinks with toppings(if they have)
/addItem -> adds new items to the cart if the requested item is in the menu.
/finalize -> returns original amount and discounted amount of the current cart.
/create/{userId} -> (prerequisite= /addItem must be called beforehand in order to transfer the cart contents to a specific userId.) assigns the current cart to a userId.
Testing-------------------------------
****ControllerUser API*****
1-)In order to see which item to add, call "/menu" endpoint.
2-)Open an api client of your choice(e.g Postman) and make a requestbody
(e.g below):
{
    "drinkName": "tea",
    "drinkToppings": [
        {
            "toppingName":"lemon"
        },
        {
            "toppingName":"chocolate sauce"
        }
    ]
}
3-)To request url enter: http://localhost:8080/addItem to add Tea with a topping lemon and chocolate sauce.
4-)Add as much drinks as you want in following manner(you can leave drinkToppings field's value empty / put just one topping and post as in step (3).
5-)Call /finalize. You see that original amount and the discounted amount for the current cart is displayed.
6-)Open postman and call create/{userId} (e.g create/1) to add the current cart to the specific user having the very userId.
7-)After step 6, call /cart endpoint to make sure that your cart is emptied
8-)You can repeat from step 1 to 6 in same manner and fill your cart and transfer the cart contents this time to another userId (e.g by calling create/2 . 
****ControllerAdmin API*****
/admin/create -> creates a new item
e.g Send the following json with Postman by calling /create
{
      "itemName": "Coca cola",
      "itemType": "drink",
      "itemPrice": 10.0
}
you see that in addition to 8 items initiated at the beginning, there is a new item having the info above.
/admin/update -> updates the item with the sent requestbody
/admin/delete/{id} -> deletes the item with the specific id.

1-) /admin/reports/total-amount-per-customer -> shows original and discounted amounts of each user's carts.
E.g
{
  "totalOrders": [
    {
      "id": 9,
      "user": 1,
      "orderGross": 19,
      "orderDiscount": 4.75,
      "orderNet": 14.25
    },
    {
      "id": 15,
      "user": 2,
      "orderGross": 26,
      "orderDiscount": 6.5,
      "orderNet": 19.5
    }
  ]
}
2-) /admin/reports/most-used-toppings-for-drinks -> shows all used toppings for all carts with the most big at the top and the lowest at the bottom.
E.g
{
  "Chocolate sauce": 3,
  "Lemon": 2
}

**Unit Tests are in StarbuxApplicationTest.java class. All unit tests can be run by right click on the file -> Run as -> Junit tests.

 
