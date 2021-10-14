# Car4U
Car4U is a web platform that allows you to book cars online. You can look through the list of current cars, choose the one you like, then select the rental period and pay for it. Also, you can search for a car. The system has 2 language: English, Russian and 3 types of roles: Guest, User, Admin. New users are given the Guest role. If the Guest is authenticates or registered, he gets the User role.
___
| Role | Description |
|------:|:--------------------:|
| Guest | Unauthenticated user |
User | Authenticated user |
Admin | Authenticated user |
	
| User status |
|-------------------|
| Active |
| Banned |
| Confirmation awaiting |

### Use cases:
1. #### Guest
+ Sign in
+ Create an account
+ look through the list of cars
+ Change language
+ Search for a car

2. #### User
+	Make an order
+	Change personal information
+	View own orders

3. #### Admin
+	Ban/unban users
+	View user list
+	Change user discount
+	View car list
+	Add new car
+	Change car discount, cost and status
+	View order list
+	Search order
+	Chang order status
