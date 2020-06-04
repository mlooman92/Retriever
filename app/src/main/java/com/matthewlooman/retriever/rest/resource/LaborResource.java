package com.matthewlooman.retriever.rest.resource;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class LaborResource {
  @SerializedName("item_identifier") private String itemIdentifier;
  @SerializedName("item_type_name") private String itemTypeName;
  @SerializedName("item_notes_text") private String itemNotesText;
  @SerializedName("item_update_timestamp") private String itemUpdateTimestamp;
  @SerializedName("labor_name") private String laborName;
  @SerializedName("labor_is_chargeable_flag") private boolean laborIsChargeableFlag;
  @SerializedName("labor_include_in_work") private boolean laborIncludeInWork;
  @SerializedName("labor_charge_to_identifier") private String laborChargeToIdentifier;
  @SerializedName("parent_labor_identifier") private String parentLaborIdentifier;

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

  public String getLaborName() {
    return laborName;
  }

  public void setLaborName(String laborName) {
    this.laborName = laborName;
  }

  public boolean isLaborIsChargeableFlag() {
    return laborIsChargeableFlag;
  }

  public void setLaborIsChargeableFlag(boolean laborIsChargeableFlag) {
    this.laborIsChargeableFlag = laborIsChargeableFlag;
  }

  public boolean isLaborIncludeInWork() {
    return laborIncludeInWork;
  }

  public void setLaborIncludeInWork(boolean laborIncludeInWork) {
    this.laborIncludeInWork = laborIncludeInWork;
  }

  public String getLaborChargeToIdentifier() {
    return laborChargeToIdentifier;
  }

  public void setLaborChargeToIdentifier(String laborChargeToIdentifier) {
    this.laborChargeToIdentifier = laborChargeToIdentifier;
  }

  public String getParentLaborIdentifier() {
    return parentLaborIdentifier;
  }

  public void setParentLaborIdentifier(String parentLaborIdentifier) {
    this.parentLaborIdentifier = parentLaborIdentifier;
  }
}
