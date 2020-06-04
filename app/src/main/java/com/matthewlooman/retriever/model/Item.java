package com.matthewlooman.retriever.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Item {
  abstract UUID getItemIdentifier();
  abstract void setItemIdentifier(UUID itemIdentifier);
  abstract String getItemTypeName();
  abstract void setItemTypeName(String itemTypeName);
  abstract LocalDateTime getItemUpdateTimestamp();
  abstract void setItemUpdateTimestamp(LocalDateTime itemUpdateTimestamp);
  abstract String getItemNotes();
  abstract void setItemNotes(String itemNotes);
}
