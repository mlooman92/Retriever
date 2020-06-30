package com.matthewlooman.retriever.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;

import java.util.UUID;

@Entity(tableName="Transition"
        ,indices={@Index(value={"transition_from_state_name"
                              , "transition_to_state_name"
                              , "transition_reason_name"
                              }
                              , unique=true
                       )
                }
       )
public class Transition extends Item {
  @ColumnInfo(name="transition_item_type_name") private String transitionItemTypeName;
  @ColumnInfo(name="transition_from_state_name") private String transitionFromStateName;
  @ColumnInfo(name="transition_from_state_ordinal") private int transitionFromStateOrdinal;
  @ColumnInfo(name="transition_to_state_name") private String transitionToStateName;
  @ColumnInfo(name="transition_to_state_ordinal") private int transitionToStateOrdinal;
  @ColumnInfo(name="transition_reason_name") private String transitionReasonName;
  @ColumnInfo(name="transition_reason_ordinal") private int transitionReasonOrdinal;
  @ColumnInfo(name="transition_definition_text") private String transitionDefinitionText;

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

  @Override
  public String toString(){
    return "(" + transitionItemTypeName + ") " + transitionFromStateName + "-->" + transitionToStateName + " because " + transitionReasonName;
  }
}
