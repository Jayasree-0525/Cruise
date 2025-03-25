package com.example.cms.controller.exceptions;  // Adjust the package name if necessary

public class SurveyNotFoundException extends RuntimeException {
  public SurveyNotFoundException(int surveyId) {
    super("Survey not found with ID: " + surveyId);
  }
}
