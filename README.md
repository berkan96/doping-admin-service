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
<img width="1314" alt="image" src="https://github.com/berkan96/doping-admin-service/assets/79544583/72813562-9188-4c3b-a1e8-6df11156c101">

- The student sends the test she solved
  <img width="1445" alt="image" src="https://github.com/berkan96/doping-admin-service/assets/79544583/5c399609-e383-430b-8e83-f2ecd624113f">
- The student examines performance in the test solved.
  <img width="1463" alt="image" src="https://github.com/berkan96/doping-admin-service/assets/79544583/185472dc-2b2d-4a43-b1e5-77191d178653">
- The student can check performance on all the tests
  <img width="1467" alt="image" src="https://github.com/berkan96/doping-admin-service/assets/79544583/285a0384-6b10-4293-af2e-f6fb01d9c00f">
- The student can check performance or answers for tests
  <img width="1410" alt="image" src="https://github.com/berkan96/doping-admin-service/assets/79544583/9ad0eadf-c72e-4fa3-b46e-737fe85794f1">






## Caching
The student wants to view all test performances. After this process, the data is cached for 20 minutes because testing times will not be less than 20 minutes. So that a new test result is added after the cache time

