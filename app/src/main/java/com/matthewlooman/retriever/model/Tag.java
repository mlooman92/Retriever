package com.matthewlooman.retriever.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(tableName = "Tag")
public class Tag extends Item {
  @ColumnInfo(name="category_name") private String categoryName;
  @ColumnInfo(name="category_definition_text") private String categoryDefinitionText;

  public String getCategoryName() { return categoryName; }
  public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
  public String getCategoryDefinitionText() { return categoryDefinitionText; }
  public void setCategoryDefinitionText(String categoryDefinitionText) { this.categoryDefinitionText = categoryDefinitionText; }

  @Override
  public String toString(){
    return this.categoryName;
  }
}
