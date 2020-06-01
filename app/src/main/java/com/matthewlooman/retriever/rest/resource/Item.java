package com.matthewlooman.retriever.rest.resource;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item<T> {
  @SerializedName("items")
  private List<T> items;

  @SerializedName("hasMore")
  private boolean hasMore;

  @SerializedName("limit")
  private int limit;

  @SerializedName("offset")
  private int offset;

  @SerializedName("count")
  private int count;

  @SerializedName("links")
  private List<ItemLinks> itemLinks;

  public List<T> getItems(){ return items;}

  public void setItems(List<T> items){ this.items = items;}

  public boolean hasMore() {
    return hasMore;
  }

  public void setHasMore(boolean hasMore) {
    this.hasMore = hasMore;
  }

  public int getLimit() {
    return limit;
  }

  public void setLimit(int limit) {
    this.limit = limit;
  }

  public int getOffset() {
    return offset;
  }

  public void setOffset(int offset) {
    this.offset = offset;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public List<ItemLinks> getItemLinks() {
    return itemLinks;
  }

  public void setItemLinks(List<ItemLinks> itemLinks) {
    this.itemLinks = itemLinks;
  }
}
