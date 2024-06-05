# Minor Project: Railway Management System

A Java-based Railway Management System using Swing for the GUI and JDBC for database connectivity.

## Introduction

This project focuses on creating a streamlined, efficient, and user-friendly platform to manage various aspects of railway operations.

## Features

- *Train schedule management*
- *User Login and Registration*
- *Ticket Booking and Cancellation*
- *Passenger Information Management*
- *Admin login for System Management*

## Installation

### Prerequisites

- *Java 8 or higher*
- *MySQL relational database*

### Clone the repository
```
git clone https://github.com/tanishavyastv/railway-management-system.git
```
### Database Setup

- *Install MySQL*
- *Set username as root and password as 1234*
- *Create a database called Railway*

## Create MySQL Tables

```MySQL
create table train (
tnum int,
tname varchar(255),
seats int,
bp varchar(255),
dp varchar(255),
fAC int,
sAC int,
tAC int,
sc int,
doj date,
atime varchar(20),
dtime varchar(20),
sno int PRIMARY KEY auto_increment
);

create table user (
uname varchar(10),
pass varchar(10),
age int,
g varchar(10),
sno int PRIMARY KEY auto_increment
);

create table chart (
sno int PRIMARY KEY auto_increment,
ticketID varchar(10),
tnum int,
pnr varchar(20),
name varchar(20),
age int,
gender varchar(10),
seatno int,
coach varchar(20),
dot date
);

```

## Running The Application

To run the application using an IDE, follow these steps:

1. Open the railway-management-system folder in VSCode, NetBeans, Eclipse, or any other IDE.
2. Locate the Railway.java file.
3. Run the Railway.java file from the IDE.

### Visual Studio Code (VSCode)
1. Open the folder in VSCode.
2. Navigate to the Railway.java file in the Explorer panel.
3. Right-click the file and select "Run Java".

### NetBeans
1. Open the folder as a project in NetBeans.
2. Locate the Railway.java file in the Projects panel.
3. Right-click the file and select "Run File".

### Eclipse
1. Open the folder as a project in Eclipse.
2. Locate the Railway.java file in the Package Explorer.
3. Right-click the file and select "Run As" > "Java Application".

## Application Screenshots

Here are some screenshots of the application:

### Main Dashboard
![Main Dashboard](https://github.com/tanishavyastv/Railway-Management-System/assets/165962059/a09de986-f716-4aa0-ab54-edc57c4579e5)

### User Login & Register Screen
![User Login & Register Screen](https://github.com/tanishavyastv/Railway-Management-System/assets/165962059/82e38328-c6d5-4cbb-88e0-17575a199221)

### Signup Screen
![Signup Screen](https://github.com/tanishavyastv/Railway-Management-System/assets/165962059/05c252b0-3945-4769-a554-13a998c6627b)

### Login Screen
![Login Screen](https://github.com/tanishavyastv/Railway-Management-System/assets/165962059/7da0e9b0-1a9d-4fcd-9316-56228f0aee44)

### Admin Login Screen
![Admin Login Screen](https://github.com/tanishavyastv/Railway-Management-System/assets/165962059/f3359411-2a76-4343-8b40-c1c181b7c39a)

### Admin Menu Screen
![Admin Menu Screen](https://github.com/tanishavyastv/Railway-Management-System/assets/165962059/250526b0-173f-4236-bfd1-705b136cf63e)

### Ticket Display Screen
![Ticket Display Screen](https://github.com/tanishavyastv/Railway-Management-System/assets/165962059/edd690d9-b621-4c63-8a57-a7acd5efe109)
