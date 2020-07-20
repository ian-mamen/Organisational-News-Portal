# Organisational News Portal

Organisational News Portal is a REST API for querying and retrieving scoped news and information.
There should be news/articles/posts that are available to all employees without navigating 
into any department, and others that are housed/classified within departments.

## Author
- [Ian Amuga](https://github.com/ian-mamen)

## Setup/Installation Requirements
* Fork this repo
* Clone this repo 
* Open terminal
* Navigate to appropriate directory using the cd command

## Setup Requirements for Database
* input the command psql <create.sql in your terminal to create the databases


## API documentation
### User
* Creating user  https://organisational-news-portal-api.herokuapp.com/users/new \
{ \
  "name":"Ian Amuga"\
  "position":"Manager",\
  "staff_role":"Writer"\
 }
 * Viewing User  https://organisational-news-portal-api.herokuapp.com/users 
 * Viewing Specific User https://organisational-news-portal-api.herokuapp.com/user/:id \
 Replace :id with id of user
 * Viewing Specific User Departments  https://organisational-news-portal-api.herokuapp.com/users/:id/departments \
 Replace :id with id of user
 
 ### Departments
 * Creating Departments  https://organisational-news-portal-api.herokuapp.com/departments/new \
{ \
  "name":"Editing",\
  "description":"Editing of books"\
 }
 * Viewing Departments  https://organisational-news-portal-api.herokuapp.com/departments 
 * Viewing Specific Department https://organisational-news-portal-api.herokuapp.comdepartment/:id \
 Replace :id with id of department
 * Viewing Specific User in Departments  https://organisational-news-portal-api.herokuapp.com/department/:id/users \
 Replace :id with id of department
 * Adding users to department in Departments  https://organisational-news-portal-api.herokuapp.com/add/user/:user_id/department/:department_id \
 Replace :id with id of department
 
 ### News
 * Creating General News https://organisational-news-portal-api.herokuapp.com/news/new/general \
{ \
  "title":"Meeting",\
  "description":"Discussion about expanding",\
  "user_id":1 \
 }
 * Creating Department News https://organisational-news-portal-api.herokuapp.com/news/new/department \
{ \
  "title":"Meeting",\
  "description":"Discussion about expanding",\
  "department_id":1, \
  "user_id":1 \
 }
 * Viewing general news  https://organisational-news-portal-api.herokuapp.com/news/general 
 * Viewing department news https://organisational-news-portal-api.herokuapp.com/news/department/:id 
 

## Technologies Used
* Java
* Heroku
* Postgresql
## Support and contact details
If you come across any problems you can reach me at: amuga72@gmail.com

### License
*This project is licensed under the terms of the MIT license.*
Copyright (c) 2020 **Ian Aamuga**

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
