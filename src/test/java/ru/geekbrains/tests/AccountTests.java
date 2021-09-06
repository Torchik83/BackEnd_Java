package ru.geekbrains.tests;

import dao.AccountResponse;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;

import static base.Endpoints.GET_ACCOUNT;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AccountTests extends BaseTest {
    ResponseSpecification accountResponseSpec;

    @Test
    void getAccountPositiveTest() {
        accountResponseSpec = positiveResponseSpecification
                .expect()
                .body("data.url", equalTo(username));

        AccountResponse response = given(requestSpecification, accountResponseSpec)
                .get(GET_ACCOUNT, username)
                .prettyPeek()
                .then()
                .extract()
                .as(AccountResponse.class);
        assertThat(response.getData().getId(), equalTo(userId));
    }
}