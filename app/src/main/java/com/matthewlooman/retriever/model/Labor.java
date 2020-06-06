package com.matthewlooman.retriever.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import java.util.UUID;

@Entity(tableName="Labor")
public class Labor extends Item {
  @ColumnInfo(name="labor_name")  private String laborName;
  @ColumnInfo(name="labor_is_chargeable_flag") private boolean laborIsChargeableFlag;
  @ColumnInfo(name="labor_include_in_work") private boolean laborIncludeInWork;
  @ColumnInfo(name="labor_charge_to_identifier") private UUID laborChargeToIdentifier;
  @ColumnInfo(name="parent_labor_identifier") private UUID parentLaborIdentifier;

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

  public UUID getLaborChargeToIdentifier() {
    return laborChargeToIdentifier;
  }

  public void setLaborChargeToIdentifier(UUID laborChargeToIdentifier) {
    this.laborChargeToIdentifier = laborChargeToIdentifier;
  }

  public UUID getParentLaborIdentifier() {
    return parentLaborIdentifier;
  }

  public void setParentLaborIdentifier(UUID parentLaborIdentifier) {
    this.parentLaborIdentifier = parentLaborIdentifier;
  }

  @Override
  public String toString(){return this.laborName;}
}
