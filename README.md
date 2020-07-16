# Rabobank Customer Statement Processor 

Rabobank receives monthly deliveries of customer statement records. This application is used to validate the customer statements.

### Tech Stack

* [Java 11](http://jdk.java.net/11/)
* [Spring Boot](https://spring.io/projects/spring-boot/)
* [Maven](https://maven.apache.org)
* [Swagger UI](https://swagger.io/tools/swagger-ui/)


### Application Functionality

#### Description
a REST service which receives the customer statement JSON as a POST data and Perform the below validations

* All transaction references should be unique 
* The end balance needs to be validated ( Start Balance +/- Mutation = End Balance )

### Getting Started

If you're going to build this project

Cloning source files `git clone <project.git>` , modify the user properties in `application.properties`.

### API Details

#### Swagger Details with Port

[Localhost Swagger](http://localhost:8085/swagger-ui.html)

#### Sample Input Format

`
[
  {
    "accountNumber": "string",
    "description": "string",
    "endBalance": number,
    "mutation": number,
    "reference": number,
    "startBalance": number
  }
]
`
#### Sample Output Format

Http Status Code  | Condition | Response format 
--- | --- | --- 
200 | When there are no duplicate reference and correct end balance | `{"result" : "SUCCESSFUL","errorRecords" : []}`
200 | When there are duplicate reference and correct balance | `{"result" : "DUPLICATE_REFERENCE","errorRecords" : [{ “reference": reference_of_error_record, "accountNumber":"account_number_of_error_record" } ]}`
200 | When there are no duplicate reference and In correct balance | `{"result" : " INCORRECT_END_BALANCE","errorRecords" : [{“reference": reference_of_error_record, "accountNumber":"account_number_of_error_record", }]}`
200 | When there are duplicate reference and In correct balance  | `{ "result" : " DUPLICATE_REFERENCE _INCORRECT_END_BALANCE", "errorRecords" : [ { “reference": reference_of_duplicate_record, "accountNumber":"account_number_of_duplicate_record", }, { “reference": reference_of_inCorrectEndBalance_record, "accountNumber":"account_number_of_ inCorrectEndBalance _record" } ] }`
400 | Error during parsing JSON  | `{ "result" : "BAD_REQUEST", "errorRecords" : [] }`
500 | Any other situation | `{ "result" : "INTERNAL_SERVER_ERROR", "errorRecords" : [] }`
 

## Authors

* **Mani Kasi** - *Initial work* - [KMani Zoro](https://github.com/kmanizoro)

## License

This project is licensed not under any License.
