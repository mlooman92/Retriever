package com.matthewlooman.retriever.room;

import android.util.Log;

import androidx.room.TypeConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class DataTypeConverters {
  private static final String TAG = "DataTypeConverters";
  @TypeConverter
  public static String fromLocalDateTimeToString(LocalDateTime localDateTime){
    if(localDateTime==null) return null;
    return localDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
  }

  @TypeConverter
  public static LocalDateTime fromStringToLocalDateTime(String string){
    if(string==null) return null;
    return LocalDateTime.parse(string,DateTimeFormatter.ISO_OFFSET_DATE_TIME);
  }

  @TypeConverter
  public static String fromUUIDtoString(UUID uuid){
    if(uuid==null) return null;
    return uuid.toString().replace("-","");
  }

  @TypeConverter
  public static UUID fromStringToUUID(String string){
    if(string==null) return null;
    String working = string.replace("-","");
    String builder = (working.substring(0,8) + "-")
            .concat(working.substring(8,12) + "-")
            .concat(working.substring(12,16) + "-")
            .concat(working.substring(16,20) + "-")
            .concat(working.substring(20));
    return UUID.fromString(builder);
  }
}
