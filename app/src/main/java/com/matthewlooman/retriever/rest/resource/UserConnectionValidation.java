package com.matthewlooman.retriever.rest.resource;

import com.google.gson.annotations.SerializedName;

public class UserConnectionValidation {
  @SerializedName("current_user")
  private String mCurrentUser;

  public UserConnectionValidation(){}

  public UserConnectionValidation(String currentUser){
    mCurrentUser = currentUser;
  }
  public String getCurrentUser() {
    return mCurrentUser;
  }

  public void setCurrentUser(String username) {
    this.mCurrentUser = username;
  }
}
