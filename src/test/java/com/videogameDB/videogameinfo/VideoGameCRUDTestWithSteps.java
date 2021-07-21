package com.videogameDB.videogameinfo;

import com.videogameDB.constants.EndPoints;
import com.videogameDB.model.VideoGamePojo;
import com.videogameDB.testbase.TestBase;
import com.videogameDB.utils.TestUtils;
import io.restassured.http.ContentType;
import net.bytebuddy.implementation.bind.annotation.Super;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static net.serenitybdd.rest.SerenityRest.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

@RunWith(SerenityRunner.class)
public class VideoGameCRUDTestWithSteps extends TestBase {
    static int id = Integer.parseInt(1 + TestUtils.getRandomValue());
    static String name = "Super Mario III";
    static String releasedate = "2021-07-14T19:24:01.924Z";
    static int reviewscore = 99;
    static String category = "Jump";
    static String rating = "U";
     static int videoGameId;

    @Steps
    VideoGameSteps videoGameSteps;

    @Title("This will create a new videogame")
    @Test
    public void test001() {
        videoGameSteps.createVideoGame(id, name, releasedate, reviewscore, category, rating).log().all()
                .statusCode(200)
                .extract().response().body();
    }

    @Title("Verify if the videogame was added to the application")
    @Test
    public void test002() {
        videoGameSteps.getVideoGameById(id).statusCode(200).log().all();
    }
    @Title("Updating VideoGame by id")
    @Test
    public void test003(){
    name = name + "_Updated";
   category = category + "_Updated";
    videoGameSteps.updateVideoGame(id,name,releasedate,reviewscore,category,rating).statusCode(200);
    //videoGameSteps.updateVideoGame(id,name,releasedate,reviewscore,category,rating);
            //.body("name",equalTo(name),"category",equalTo(category));

    }
    @Title("Deleting VideoGame by id")
    @Test
    public void test004(){
        videoGameSteps.deleteVideoGame(id).statusCode(200);
        videoGameSteps.getVideoGameById(id).statusCode(500);
    }

}

