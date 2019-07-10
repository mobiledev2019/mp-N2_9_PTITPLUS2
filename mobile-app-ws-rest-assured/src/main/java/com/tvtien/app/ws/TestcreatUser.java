package com.tvtien.app.ws;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.arjuna.ats.internal.arjuna.objectstore.jdbc.drivers.postgres_driver;

import io.restassured.RestAssured;
import io.restassured.response.Response;

class TestcreatUser {

	private final String CONTEXT_PATH = "/mobile-app-ws";

	@BeforeEach
	void setUp() throws Exception {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
	}

	@Test
	void testCreatUser() {
		
		
		
		
		Response response =given()
		.contentType("application/json")
		.accept("application/json")
		.body(null)
		.when()
		.post(CONTEXT_PATH + "/users")
		.then()
		.startsCode(200)
		.contentType("application/json")
		.exctract()
		.response();
	}

}
