<img src="./homework-1/figure/logo.jpg" width="250px">

# Wascoot 

WASCOOT is a scooter management web application. It allows the company to keep track of scooters, their locations, and their usage patterns.
The application provides data analytics and reporting features, allowing the company to track usage patterns, measure performance, and make data-driven decisions.
## Group members

| Surname       | Name          | ID       |
| ------------- | ------------- |----------|
| **Crivellari**	| **Alberto**	| 2061934	 |
| Gharehzad		| Shiva	| 2041385  |
| Huang	| Borwoei	| 2044019	 |
| kuijpers        | nickclauder	| 2096202	 |
| Mada | Sreeshalini | 2049543  |
| Niknamhesar | Sara | 2085443  |
| Tahan		| Paria		| 2043889	 |


## Project Description ##

This project aims at developing a web application related to our Web Application course from the University of Padova from Professor Nicola Ferro. 

It's a tool for managers of a scooter business, with the aim of improving the efficiency and control over their operations.

## Replicability ##

Clone this repository and change pom.xml and context.xml file to adapt your local settings (**pom.xml** for java dependencies and **context.xml** for database settings).

Build procedure is done with maven : mvn clean package.

## Organisation of the repository ###

The repository is organised as follows:

* `src` This folder contains the source code of the developed web application.
    * `main` :
        * `database` This folder we have the `.sql` files for the database (create, populate, test)
        * `java` This folder contains the backend of the web application. It contains `wascoot` folder, which contains 6 subfolders: `database`, which contains classes to interact with the DB, `resource`, `rest`, which contains classes for the RESTful APIs, `servlet`, which contains classes for servlets, `utils` and `filter`. 
        * `resources`
        * `webapp` This folder contains the frontend of the web application. It contains 7 subfolders: `css`, `html`, `js`, `jsp`, Java Server Page, which are the core of the frontend, `media`, `META-INF`, with the **context.xml** and `WEB-INF`, with configuration file for REST and servlets URIs and other configuration files.
            
* `homework-1`: This folder contains the report describing the structure of the database and our backend.
* `homework-2`: This folder contains the presentation of our web application.
