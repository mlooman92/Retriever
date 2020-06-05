package com.matthewlooman.retriever.rest.resource;

import com.google.gson.annotations.SerializedName;

public class TransitionResource {
  @SerializedName("item_identifier") private String itemIdentifier;
  @SerializedName("item_type_name") private String itemTypeName;
  @SerializedName("item_notes_text") private String itemNotesText;
  @SerializedName("item_update_timestamp") private String itemUpdateTimestamp;
  @SerializedName("transition_item_type_name") private String transitionItemTypeName;
  @SerializedName("transition_from_state_name") private String transitionFromStateName;
  @SerializedName("transition_from_state_ordinal") private int transitionFromStateOrdinal;
  @SerializedName("transition_to_state_name") private String transitionToStateName;
  @SerializedName("transition_to_state_ordinal") private int transitionToStateOrdinal;
  @SerializedName("transition_reason_name") private String transitionReasonName;
  @SerializedName("transition_reason_ordinal") private int transitionReasonOrdinal;
  @SerializedName("transition_definition_text") private String transitionDefinitionText;

  public String getItemIdentifier() {
    return itemIdentifier;
  }

  public void setItemIdentifier(String itemIdentifier) {
    this.itemIdentifier = itemIdentifier;
  }

  public String getItemTypeName() {
    return itemTypeName;
  }

  public void setItemTypeName(String itemTypeName) {
    this.itemTypeName = itemTypeName;
  }

  public String getItemNotesText() {
    return itemNotesText;
  }

  public void setItemNotesText(String itemNotesText) {
    this.itemNotesText = itemNotesText;
  }

  public String getItemUpdateTimestamp() {
    return itemUpdateTimestamp;
  }

  public void setItemUpdateTimestamp(String itemUpdateTimestamp) {
    this.itemUpdateTimestamp = itemUpdateTimestamp;
  }

  public String getTransitionItemTypeName() {
    return transitionItemTypeName;
  }

  public void setTransitionItemTypeName(String transitionItemTypeName) {
    this.transitionItemTypeName = transitionItemTypeName;
  }

  public String getTransitionFromStateName() {
    return transitionFromStateName;
  }

  public void setTransitionFromStateName(String transitionFromStateName) {
    this.transitionFromStateName = transitionFromStateName;
  }

  public int getTransitionFromStateOrdinal() {
    return transitionFromStateOrdinal;
  }

  public void setTransitionFromStateOrdinal(int transitionFromStateOrdinal) {
    this.transitionFromStateOrdinal = transitionFromStateOrdinal;
  }

  public String getTransitionToStateName() {
    return transitionToStateName;
  }

  public void setTransitionToStateName(String transitionToStateName) {
    this.transitionToStateName = transitionToStateName;
  }

  public int getTransitionToStateOrdinal() {
    return transitionToStateOrdinal;
  }

  public void setTransitionToStateOrdinal(int transitionToStateOrdinal) {
    this.transitionToStateOrdinal = transitionToStateOrdinal;
  }

  public String getTransitionReasonName() {
    return transitionReasonName;
  }

  public void setTransitionReasonName(String transitionReasonName) {
    this.transitionReasonName = transitionReasonName;
  }

  public int getTransitionReasonOrdinal() {
    return transitionReasonOrdinal;
  }

  public void setTransitionReasonOrdinal(int transitionReasonOrdinal) {
    this.transitionReasonOrdinal = transitionReasonOrdinal;
  }

  public String getTransitionDefinitionText() {
    return transitionDefinitionText;
  }

  public void setTransitionDefinitionText(String transitionDefinitionText) {
    this.transitionDefinitionText = transitionDefinitionText;
  }
}
