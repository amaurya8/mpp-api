package com.aisa.mpp.api.mppapi.group;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GroupInfoApiTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test()
    @Order(1)
    public void testAddGroup() {
        String jsonPayload = "{\n" +
                "  \"id\": 1,\n" +
                "  \"grpName\": \"Study Group\",\n" +
                "  \"grpInfo\": \"A group for studying advanced topics.\",\n" +
                "  \"noOfMember\": 10,\n" +
                "  \"startDate\": \"2024-09-01\",\n" +
                "  \"endDate\": \"2024-12-01\",\n" +
                "  \"venue\": \"Library Room 3\",\n" +
                "  \"avlPosition\": 5\n" +
                "}";

        given()
                .contentType("application/json")
                .body(jsonPayload)
                .when()
                .post("/api/groups")
                .then()
                .statusCode(200)
                .body("grpName", equalTo("Study Group"));
    }

    @Test
    @Order(2)
    public void testGetAllGroups() {
        given()
                .when()
                .get("/api/groups")
                .then()
                .statusCode(200);
    }

    @Test
    @Order(3)
    public void testGetGroupBy1Name() {
        given()
                .param("grpName", "Study Group")
                .when()
                .get("/api/groups/findByGrpName")
                .then()
                .statusCode(200)
                .body("id", equalTo(1));
    }
    @Test
    @Order(4)
    public void testGetGroupById() {
        given()
                .param("Id", "1")
                .when()
                .get("/api/groups/findByGrpId")
                .then()
                .statusCode(200)
                .body("grpName", equalTo("Study Group"));
    }

}