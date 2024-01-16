# Zootopia

## Assumptions made

- Admin username = `admin` ; Admin password = `admin123`
- The unique IDs of all attractions, zoo animals and visitors start from `1001`
- The type of animal entered as input are singular only.
- When `Manage Animals` menu is open, it cannot be exited unless 2 animals of each type are added. If no animal is added i.e this menu was never opened, user menu will simply display a console message saying `No animal added`
- The default status of all attractions is `OPEN`. When the admin chooses to change the status, they simply switch the status i.e `OPEN` becomes `CLOSE` and `CLOSE` becomes `OPEN` without taking any additional input.
- If the price for an attraction is not set by the admin, then the default price is `0` i.e the attraction is free of cost for everyone. However, the basic membership members still need to buy the free tickets to visit an attraction.
- There can be multiple popular attractions. Sorting the array changes the initial order in which the input was taken.
- The discount code is created by concatenating the first word of the category and the percentage discount. Modifying the category or percentage modifies the code accordingly.
- Special Deals `Hashmap` has a hard coded value for 1 attraction which has a discount of 0 percent.
- Email id of a visitor serves as a unique identifier i.e multiple accounts with same email id cannot be created. However, similar restrictions haven't been added for phone numbers.
- Email id is not case sensitive.
- On choosing the `Explore the Zoo` menu, the `View Attractions` show description of the attraction and `View Animals` displays the type of the animal.
- While making any purchase, if the visitor's balance falls below 0 then they cannot make purchases anymore.
- A visitor cannot perform any activity if they haven't bought the membership.
- A special deals for `n` tickets is only applied if greater than or eqaul to `n` tickets are bought together.
- Discount and special deal are applied together in the following way: first the cost of the ticket is reduced by discount percent. Then the discount offered by the special deal is applied on the reduced price on tickets together.
- Premium members may or may not buy the tickets. However, on buying tickets, their balance remains unchanged.
- Discount codes and special deals are reusable.
- If the status of the attraction is set to `CLOSE` by the admin, it will still be displayed in the menu for the user. However, the user cannot buy the tickets or visit the attractions.
- Membership can be bought again and again and membership can be changed too.
- For discount codes with category mentioning `minor` and `student`, the age limit is below 18 and or those mentioning senior, the age limit is above 60. Users in invalid age range cannot use these codes.
- The popularity of an attraction is decided by tickets sold. For premium members, if they visit an attraction without buying a tickets, the `tickets` parameter for the attraction is incremented by 1 to keep a track of its popularity. The updated tickets are displayed in the `Schedule Events` menu.
- The revenue generated is decided by the cost of tickets sold and membership bought, along with the discount applied on them.
-`None` of the values are hard coded (except admin information) and has to be taken through user input.

## Running the code
HOME_FOLDER = src

All the commands should be run on the terminal of the main folder i.e the `zootopia` folder, instead of `src`

0) Download the `zootopia` code folder from Classroom and unzip.
1) mvn clean 
2) mvn compile
3) mvn package
4) cd target
5) For `main` function in `zoo.java`(running the entire code): java -jar zootopia-1.0-SNAPSHOT.jar