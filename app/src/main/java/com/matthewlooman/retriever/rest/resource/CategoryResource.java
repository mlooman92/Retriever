package com.matthewlooman.retriever.rest.resource;

import com.google.gson.annotations.SerializedName;

public class CategoryResource {
  @SerializedName("item_identifier") private String itemIdentifier;
  @SerializedName("item_type_name") private String itemTypeName;
  @SerializedName("item_notes_text") private String itemNotesText;
  @SerializedName("item_update_timestamp") private String itemUpdateTimestamp;
  @SerializedName("category_name") private String categoryName;
  @SerializedName("category_definition_text") private String categoryDefinitionText;

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

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getCategoryDefinitionText() {
    return categoryDefinitionText;
  }

  public void setCategoryDefinitionText(String categoryDefinitionText) {
    this.categoryDefinitionText = categoryDefinitionText;
  }
}
