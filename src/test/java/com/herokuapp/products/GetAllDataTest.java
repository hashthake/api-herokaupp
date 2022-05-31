package com.herokuapp.products;

import com.herokuapp.model.StudentPojo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GetAllDataTest {

    @Test
    public void getGetBookingIds() {
        StudentPojo studentPojo = new StudentPojo();

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(studentPojo)
                .when()
                .post("/{/booking}");
        response.prettyPrint();
        response.then().statusCode(200);

    }
    @Test
    public void deleteId() {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .auth().preemptive().basic("admin", "password123")
                .pathParam("id", 2910)
                .when()
                .delete("/booking/{id}");

        response.then().time(lessThan(3000L));
        response.prettyPrint();
        response.then().statusCode(201);
    }
        @Test
        public void getBookingId(){
            Response response = given()
                    .when()
                    .pathParam("id",1)
                    .get("/booking/{id}");
            response.then().statusCode(200);
            response.prettyPrint();
        }
        @Test
        public void getAllBookingId(){
            Response response = given()
                    .when()
                    .get("/booking");
            response.then().statusCode(200);
            response.prettyPrint();
        }
        @BeforeClass
        public static void inIt() {
            RestAssured.baseURI = "https://restful-booker.herokuapp.com";
        }
}
