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

## Important Endpoints
- Create Student.
<img width="1491" alt="image" src="https://github.com/berkan96/doping-admin-service/assets/79544583/00a4e354-3154-4369-b353-1a4d53587a21">

- The test is recorded in the system.
  <img width="1463" alt="image" src="https://github.com/berkan96/doping-admin-service/assets/79544583/9a3fbc11-d817-409d-828b-1198beece129">

- The student sends the test she solved
  <img width="1482" alt="image" src="https://github.com/berkan96/doping-admin-service/assets/79544583/abb54d4b-9845-44d0-8982-bf9a77e148cc">

- The student examines performance in the test solved.
  <img width="1528" alt="image" src="https://github.com/berkan96/doping-admin-service/assets/79544583/98b5bf3a-09bc-4275-86bc-8f5a2012d416">

- The student can check performance on all the tests
  <img width="1440" alt="image" src="https://github.com/berkan96/doping-admin-service/assets/79544583/5480b245-3853-4dc8-902b-9eb43db3a84e">

- The student can check performance or answers for tests
  <img width="1429" alt="image" src="https://github.com/berkan96/doping-admin-service/assets/79544583/e581a80d-3b01-4fde-805b-86ac66859c0f">







## Caching
The student wants to view all test performances. After this process, the data is cached for 20 minutes because testing times will not be less than 20 minutes. So that a new test result is added after the cache time

