# Recipe Management System
A system aimed toward efficient and convenient recipe database modification and implementation. Users can add, update, and delete recipes easily. Recipe search is made to be very clear and user-friendly.
## Project requirements: 
- user authentification - user can sign up and data about them will be added to recipe database;
- creating recipe - user can create new data and add it to database;
- searching recipe - search function works on keywords, finding the most compatible result;
- information about recipe - user has full access to the data of the recipe in separate Recipe page;
- data modification - user has access to update and/or delete recipe, all changes will be saved in database.
## Team members list:
- Urmatai Toktosunova - in charge of creating tables, establishing the relationships between tables, preparing structured sample data, making the presentation part;
- Saltanat Umarova - in charge of creating the application, connecting database and its implementation.
## Key aspects of the project:
- HelloController.java > initialize() -
  takes the data from database through SQL query > creates observable list and puts all the data into it, then sets values from list into Table View;
                     (https://github.com/urmataj/Final_Assignment_OOP/assets/145670962/e7125c2c-7c59-4654-a205-fc86571ff8b3)
![Снимок экрана (32)](https://github.com/urmataj/Final_Assignment_OOP/assets/145670962/27583578-9bba-44e6-aa69-49ddf4f0a348)
![Снимок экрана (33)](https://github.com/urmataj/Final_Assignment_OOP/assets/145670962/bbd42f95-f74a-4004-a69e-1729c6fb796e)
![Снимок экрана (34)](https://github.com/urmataj/Final_Assignment_OOP/assets/145670962/781a3018-6663-47c8-bc18-79c116bb8069)
![Снимок экрана (35)](https://github.com/urmataj/Final_Assignment_OOP/assets/145670962/8163d98e-5e1e-4d64-839e-bbc8761da094)
                        > refresh() - in case of adding, updating, deleting data - table's values will be also updated.
  ![Снимок экрана (36)](https://github.com/urmataj/Final_Assignment_OOP/assets/145670962/0263fa19-d8a6-4a1a-adae-eb2ac826d35a)
![Снимок экрана (37)](https://github.com/urmataj/Final_Assignment_OOP/assets/145670962/cea60952-756a-4e6a-808f-d26253a2a501)

- Recipe.java - class that represents recipe.
![Снимок экрана (38)](https://github.com/urmataj/Final_Assignment_OOP/assets/145670962/10d6d244-cbb8-4ed4-ab07-701005278662)

- RecipeController.java > addRecipe() - takes values of text fields inputs, through SQL query, adds values to recipe table in database;
  ![Снимок экрана (39)](https://github.com/urmataj/Final_Assignment_OOP/assets/145670962/b7a5101b-a184-44b5-a698-2b9a138fadc5)
                      > deleteRecipe() - uses delete query and based on title of recipe will find and delete it from database.
  ![Снимок экрана (40)](https://github.com/urmataj/Final_Assignment_OOP/assets/145670962/78975880-dfcb-4f51-9b7c-fb9cbdb84ca7)
- UpdateController.java > updateRecipe() - takes values from RecipeController and using update statement changes certain data, updated data will be put in the database.
![Снимок экрана (41)](https://github.com/urmataj/Final_Assignment_OOP/assets/145670962/d53b1f66-0f20-4d8c-922f-0454eff27fc1)
- UserController.java > signupUser() - gets values from text field inputs, through sql query adds data to user table in database.
![Снимок экрана (42)](https://github.com/urmataj/Final_Assignment_OOP/assets/145670962/936c1194-a200-47fe-99b1-6495937b63f9)


