This API is used to connect to AdventureWorks db

# Project structure

| Package    | Description                                                                                   | 
|------------|-----------------------------------------------------------------------------------------------|
| business   | contains all the use cases (Create, Update, Delete, Find) and the interfaces for repositories |
| config     | contains the bean configuration for repositories                                              |
| controller | contains the rest controllers                                                                 |
| dto        | contains the objects used to send response                                                    |
| exceptions | contains the exceptions and configuration to handled it                                       |
| repository | contains the repository implementation                                                        |

### About the test

The project contains unit test for the business use case

### About the repositories

All the repositories are implemented using stored procedures
