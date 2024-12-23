# Personal Finance Application

## RoadMap

- [ ] Add automated testing for each service
- [ ] Build Analytics service
- [ ] Build reccomendation service to make suggestions for catagories, limit values etc based on user history **stretch goal**

## Purpose

The **Personal Finance Application** is designed to help users manage their personal finances on a month-to-month basis. With a simple and intuitive interface, users can create budgets, track their spending, and monitor when they exceed their budget. The application aims to provide users with insights into their financial habits and improve their budgeting skills.

## Key Features

- **Create and Edit Monthly Budgets**: Users can set up their budgets for each month.
- **Log and Categorize Spending**: Track spending by date, amount, and category (e.g. food, transportation).
- **Monitor Budget Overruns**: Receive feedback when spending exceeds the allocated budget for any category or overall.
- **View Spending Reports**: Month-to-month reports displaying spending trends (stretch goal).

## User Requirements

- Users can create an account, log in, and store their financial data.
- A simple and user-friendly interface to ensure smooth navigation.
- Provide users with feedback when they exceed their budget.

## Technical Requirements

- **Backend**: Java for business logic.
- **Database**: PostgreSQL database.
- **Frontend**: React.js for building the user interface.
- **API**: A RESTful API using Java and Spring Boot for communication between the frontend and backend.

## Application Architecture

![application architecture](architecture-diagram.jpg)

The application will follow a standard client-server architecture:

- **Frontend**: React.js for UI.
- **Backend**: Java and Spring Boot to handle business logic and data processing.
- **Database**: PostgreSQL to store user information, budgets, and spending data.

## Endpoints

### USER

- **GET** `/api/v1/user`  
  Retrieves a list of all users.

- **POST** `/api/v1/user`  
  Registers a new user.

- **DELETE** `/api/v1/user/{userId}`  
  Deletes a user by their ID.

- **PUT** `/api/v1/user/{userId}`  
  Updates user details.

### BUDGET

- **GET** `/api/v1/budget/{userId}`  
  Retrieves all monthly budgets for a specific user.

- **POST** `/api/v1/budget`  
  Creates a new monthly budget.

- **PUT** `/api/v1/budget/{budgetId}`  
  Updates a monthly budget, including category limits and total.

- **DELETE** `/api/v1/budget/{budgetId}`  
  Deletes a monthly budget by its ID.

### SPENDINGITEM

- **GET** `/api/v1/spending`  
  Retrieves spending items by user and month.

- **POST** `/api/v1/spending`  
  Adds a new spending item.

- **DELETE** `/api/v1/spending/{spendingId}`  
  Deletes a spending item by its ID.

### CATAGORY

- **GET** `/api/v1/catagory/{userId}`  
  Retrieves categories for a specific user.

- **POST** `/api/v1/catagory`  
  Adds a new category.

- **DELETE** `/api/v1/catagory/{catagoryId}`  
  Deletes a category by its ID.

- **PUT** `/api/v1/catagory/{catagoryId}`  
  Updates a category's label.

## Database Models

The application will consist of the following key models:

| **Table**        | **Column Name**  | **Type**         | **Description**                                 |
| ---------------- | ---------------- | ---------------- | ----------------------------------------------- |
| **Users**        | `id`             | Primary Key      | Unique identifier for each user                 |
|                  | `firstName`      | String           | First name of the user                          |
|                  | `lastName`       | String           | Last name of the user                           |
|                  | `username`       | String           | Username for login                              |
|                  | `password`       | Encrypted String | User's encrypted password                       |
|                  | `email`          | String           | User's email address                            |
| **Budgets**      | `id`             | Primary Key      | Unique identifier for each budget               |
|                  | `user`           | Foreign Key      | References `user_id` in `Users` table           |
|                  | `categoryLimits` | Map              | Map of Catagories and associated monthly limits |
|                  | `month`          | Date             | Budget month (e.g. '2024-09-18')                |
|                  | `total`          | Double           | Total budget amount for the month               |
| **SpendingItem** | `id`             | Primary Key      | Unique identifier for each spending entry       |
|                  | `user`           | Foreign Key      | References `user_id` in `Users` table           |
|                  | `category_id`    | Foreign Key      | References `category_id` in `Categories` table  |
|                  | `label`          | String           | Title describing what was spent                 |
|                  | `amount_spent`   | Double           | Amount spent in a category                      |
|                  | `date`           | Date             | Date of the spending (e.g. '2024-09-18')        |
| **Categories**   | `id`             | Primary Key      | Unique identifier for each category             |
|                  | `user`           | Foreign Key      | References `user_id` in `Users` table           |
|                  | `name`           | String           | Name of the spending category (e.g. 'Food')     |

## Error Handling Considerations

- **Input Validation**: Ensure data entered by users is validated before submission. For example, ensuring proper date formats and valid spending amounts.
