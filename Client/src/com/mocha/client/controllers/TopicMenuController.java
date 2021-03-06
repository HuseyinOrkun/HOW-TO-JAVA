package com.mocha.client.controllers;

import com.mocha.client.Core;
import com.mocha.client.JsonListenerCapsule.JsonListener;
import com.mocha.client.JsonListenerCapsule.RequestTypes;
import com.mocha.client.Transition;
import com.mocha.client.models.requests.QuestionRequest;
import com.mocha.client.models.requests.QuestionResultRequest;
import com.mocha.client.models.results.QuestionResults;
import com.mocha.server.models.Questions.Question;
import com.mocha.server.models.Questions.QuestionContainer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

/**
 * Topic Menu Controller. Topic Menu is the menu that shows after clicking the "Practice!" button
 * in the Main Menu.
 * Created by E.Batuhan Kaynak on 8.4.2016.
 */

public class TopicMenuController extends Controller {

    private QuestionContainer questions;
    private Question questionToShow;

    public static Question questionBadDesign;

    public TopicMenuController()
    {
        Core.JsonListenerManager.addJsonListener(RequestTypes.QUESTION_RESULT, new JsonListener<QuestionResultRequest>() {
            @Override
            public void run(QuestionResultRequest req) {
                QuestionResults res = req.getResult();
                System.out.println(res);
                if (res == QuestionResults.SUCCESS) {
                    //System.out.println("Got the questions");
                    questions = req.getQuestions();
                    questionBadDesign = questions.getQuestions().get(0);
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            goToScene("CodingMenu");
                        }
                    });
                } else if (res == QuestionResults.FAILURE) {
                    System.out.println("Rip questions");
                }
            }
        });
    }

    @FXML
    public void onDataTypesButtonClick(MouseEvent mouseEvent) {
        requestQuestions("DATA_TYPES", "1");
        //prepareQuestion();
        //goToScene("CodingMenu");
        //goToCodingMenu(questionToShow);
        //requestQuestions(TopicTypes.DATA_TYPES);
        //goToCodingMenu(TopicTypes.DATA_TYPES);
        //goToScene("CodingMenu");
    }

    @FXML
    public void onMethodsButtonClick(MouseEvent mouseEvent) {
        goToScene("CodingMenu");
    }

    public void requestQuestions(String topicType, String level)
    {
        Core.SocketManager.sendMessageObject(RequestTypes.QUESTION, new QuestionRequest(topicType, level));
    }

    /*
    public void prepareQuestion(){
        //for (int i = 0; i < 1)

        //Question questionToShow = questions.get(0);
    }*/

    public void goToCodingMenu(Question questionToShow) {
        goToScene("CodingMenu", questionToShow);
    }

    private void goToScene(String codingMenu, Question questionToShow) {
        new Transition(getPrevStage(), codingMenu, questionToShow).changeScene();
    }
}
