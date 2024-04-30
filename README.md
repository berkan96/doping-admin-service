# doping-admin-service

## Project Summary

This project is about students' test performance. After registering to the system, students can solve the tests in the system.They can see performance information for the tests they have solved.
Tests can be added to the system. Changes can be made to the tests. All test performances of the student can be learned.Test-based performance information can be learned.

## Technologies Used
- Java 17
- Springboot 3
- Redis
- Swagger
- H2 Database

## Caching
The student wants to view all test performances. After this process, the data is cached for 20 minutes because testing times will not be less than 20 minutes. So that a new test result is added after the cache time

