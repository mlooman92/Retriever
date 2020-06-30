package com.matthewlooman.retriever.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.matthewlooman.retriever.model.Category;
import com.matthewlooman.retriever.model.ItemType;
import com.matthewlooman.retriever.model.Labor;
import com.matthewlooman.retriever.model.Location;
import com.matthewlooman.retriever.model.Organization;
import com.matthewlooman.retriever.model.Party;
import com.matthewlooman.retriever.model.Person;
import com.matthewlooman.retriever.model.Priority;
import com.matthewlooman.retriever.model.Tag;
import com.matthewlooman.retriever.model.Transition;

import javax.inject.Singleton;

@Database(entities = {ItemType.class, Location.class, Labor.class, Priority.class,Transition.class
                     , Tag.class, Category.class, Party.class, Organization.class, Person.class
                     }
, version=7
)
@TypeConverters({DataTypeConverters.class})
public abstract class RetrieverDatabase extends RoomDatabase {
  // --- SINGLETON ---
  private static volatile RetrieverDatabase INSTANCE;

  public abstract ItemTypeDao itemTypeDao();

  public abstract LaborDao laborDao();

  public abstract LocationDao locationDao();

  public abstract PriorityDao priorityDao();

  public abstract TagDao tagDao();

  public abstract TransitionDao transitionDao();

  public abstract PartyDao partyDao();

  public abstract PersonDao personDao();

  public abstract OrganizationDao organizationDao();

}
