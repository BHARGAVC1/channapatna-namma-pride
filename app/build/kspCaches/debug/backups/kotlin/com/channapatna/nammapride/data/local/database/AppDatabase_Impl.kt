package com.channapatna.nammapride.`data`.local.database

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import com.channapatna.nammapride.`data`.local.dao.ArtisanDao
import com.channapatna.nammapride.`data`.local.dao.ArtisanDao_Impl
import com.channapatna.nammapride.`data`.local.dao.ToyDao
import com.channapatna.nammapride.`data`.local.dao.ToyDao_Impl
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class AppDatabase_Impl : AppDatabase() {
  private val _toyDao: Lazy<ToyDao> = lazy {
    ToyDao_Impl(this)
  }


  private val _artisanDao: Lazy<ArtisanDao> = lazy {
    ArtisanDao_Impl(this)
  }


  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(2,
        "be7840812c3b4e8096f2aa6dcbd16d05", "687c55067f8ec3a447f24d7ae70241d7") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `toys` (`toyId` TEXT NOT NULL, `name` TEXT NOT NULL, `artisanId` TEXT NOT NULL, `material` TEXT NOT NULL, `processDescription` TEXT NOT NULL, `imageUrl` TEXT NOT NULL, `isAuthentic` INTEGER NOT NULL, `verificationCode` TEXT NOT NULL, `price` TEXT NOT NULL, `category` TEXT NOT NULL, `isFavorite` INTEGER NOT NULL, PRIMARY KEY(`toyId`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `artisans` (`artisanId` TEXT NOT NULL, `name` TEXT NOT NULL, `locationText` TEXT NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `experienceYears` INTEGER NOT NULL, `craftType` TEXT NOT NULL, `bio` TEXT NOT NULL, `photoUrl` TEXT NOT NULL, PRIMARY KEY(`artisanId`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'be7840812c3b4e8096f2aa6dcbd16d05')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `toys`")
        connection.execSQL("DROP TABLE IF EXISTS `artisans`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection):
          RoomOpenDelegate.ValidationResult {
        val _columnsToys: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsToys.put("toyId", TableInfo.Column("toyId", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsToys.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsToys.put("artisanId", TableInfo.Column("artisanId", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsToys.put("material", TableInfo.Column("material", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsToys.put("processDescription", TableInfo.Column("processDescription", "TEXT", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsToys.put("imageUrl", TableInfo.Column("imageUrl", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsToys.put("isAuthentic", TableInfo.Column("isAuthentic", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsToys.put("verificationCode", TableInfo.Column("verificationCode", "TEXT", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsToys.put("price", TableInfo.Column("price", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsToys.put("category", TableInfo.Column("category", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsToys.put("isFavorite", TableInfo.Column("isFavorite", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysToys: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesToys: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoToys: TableInfo = TableInfo("toys", _columnsToys, _foreignKeysToys, _indicesToys)
        val _existingToys: TableInfo = read(connection, "toys")
        if (!_infoToys.equals(_existingToys)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |toys(com.channapatna.nammapride.data.local.entity.Toy).
              | Expected:
              |""".trimMargin() + _infoToys + """
              |
              | Found:
              |""".trimMargin() + _existingToys)
        }
        val _columnsArtisans: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsArtisans.put("artisanId", TableInfo.Column("artisanId", "TEXT", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArtisans.put("name", TableInfo.Column("name", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArtisans.put("locationText", TableInfo.Column("locationText", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArtisans.put("latitude", TableInfo.Column("latitude", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArtisans.put("longitude", TableInfo.Column("longitude", "REAL", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArtisans.put("experienceYears", TableInfo.Column("experienceYears", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsArtisans.put("craftType", TableInfo.Column("craftType", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArtisans.put("bio", TableInfo.Column("bio", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsArtisans.put("photoUrl", TableInfo.Column("photoUrl", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysArtisans: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesArtisans: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoArtisans: TableInfo = TableInfo("artisans", _columnsArtisans, _foreignKeysArtisans,
            _indicesArtisans)
        val _existingArtisans: TableInfo = read(connection, "artisans")
        if (!_infoArtisans.equals(_existingArtisans)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |artisans(com.channapatna.nammapride.data.local.entity.Artisan).
              | Expected:
              |""".trimMargin() + _infoArtisans + """
              |
              | Found:
              |""".trimMargin() + _existingArtisans)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "toys", "artisans")
  }

  public override fun clearAllTables() {
    super.performClear(false, "toys", "artisans")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(ToyDao::class, ToyDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ArtisanDao::class, ArtisanDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override
      fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>):
      List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun toyDao(): ToyDao = _toyDao.value

  public override fun artisanDao(): ArtisanDao = _artisanDao.value
}
