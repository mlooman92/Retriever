package com.matthewlooman.retriever.rest.resource;

import com.google.gson.annotations.SerializedName;

public class OrganizationResource {
  @SerializedName("item_identifier") String itemIdentifier;
  @SerializedName("item_type_name") String itemTypeName;
  @SerializedName("item_notes_text") String itemNotesText;
  @SerializedName("item_update_timestamp") String itemUpdateTimestamp;
  @SerializedName("party_name") String partyName;
  @SerializedName("party_alternative_name") String partyAlternativeName;
  @SerializedName("organization_web_domain") String organizationWebDomain;

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

  public String getPartyName() {
    return partyName;
  }

  public void setPartyName(String partyName) {
    this.partyName = partyName;
  }

  public String getPartyAlternativeName() {
    return partyAlternativeName;
  }

  public void setPartyAlternativeName(String partyAlternativeName) {
    this.partyAlternativeName = partyAlternativeName;
  }

  public String getOrganizationWebDomain() {
    return organizationWebDomain;
  }

  public void setOrganizationWebDomain(String organizationWebDomain) {
    this.organizationWebDomain = organizationWebDomain;
  }
}
