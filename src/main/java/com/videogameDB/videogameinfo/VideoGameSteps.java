package com.videogameDB.videogameinfo;

import com.videogameDB.constants.EndPoints;
import com.videogameDB.model.VideoGamePojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class VideoGameSteps {
    @Step("Creating new Video Game with ID {0}  Name {1}, ReleaseDate {2}, Review Score {3}, Category {4}, Rating {5}")

    public ValidatableResponse createVideoGame(int id, String name, String releasedate, int reviewscore, String category,
                                               String rating) {

        VideoGamePojo videoGamePojo = new VideoGamePojo();
        videoGamePojo.setId(id);
        videoGamePojo.setName(name);
        videoGamePojo.setReleaseDate(releasedate);
        videoGamePojo.setReviewScore(reviewscore);
        videoGamePojo.setCategory(category);
        videoGamePojo.setRating(rating);

        return SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(videoGamePojo)
                .post(EndPoints.CREATE_NEW_VIDEOGAME)
                .then().log().all();

    }

    @Step("Getting the VideoGame information with id : {0}")

    public ValidatableResponse getVideoGameById(int videoGameId) {
        return SerenityRest.rest()
                .given().log().all()
                .pathParam("id", videoGameId)
                .header("Accept","application/json")
                .when()
                .get(EndPoints.GET_VIDEOGAME_BY_ID)
                .then();
    }

    @Step("Creating new Video Game with ID {0} , Name {1}, ReleaseDate {2}, Review Score {3}, Category {4}, Rating {5}")
    public ValidatableResponse updateVideoGame(int id, String name, String releasedate, int reviewscore, String category,
                                               String rating) {
VideoGamePojo videoGamePojo = new VideoGamePojo();
        return SerenityRest.rest()
                .given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", id)
                 .when()
                .body(videoGamePojo)
                .accept("application/json")
                .put(EndPoints.UPDATE_VIDEOGAME_BY_ID)
                .then();

    }

    @Step("Creating new Video Game with ID {0}  Name {1}, ReleaseDate {2}, Review Score {3}, Category {4}, Rating {5}")
    public ValidatableResponse deleteVideoGame(long videoGameId) {
        return SerenityRest.rest()
                .given()
                .pathParam("id", videoGameId)
                .when()
                .delete(EndPoints.DELETE_VIDEOGAME_BY_ID)
                .then();



    }


}
