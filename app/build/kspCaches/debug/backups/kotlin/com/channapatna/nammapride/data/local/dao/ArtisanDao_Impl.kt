package com.channapatna.nammapride.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.channapatna.nammapride.`data`.local.entity.Artisan
import javax.`annotation`.processing.Generated
import kotlin.Double
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ArtisanDao_Impl(
  __db: RoomDatabase,
) : ArtisanDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfArtisan: EntityInsertAdapter<Artisan>
  init {
    this.__db = __db
    this.__insertAdapterOfArtisan = object : EntityInsertAdapter<Artisan>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `artisans` (`artisanId`,`name`,`locationText`,`latitude`,`longitude`,`experienceYears`,`craftType`,`bio`,`photoUrl`) VALUES (?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Artisan) {
        statement.bindText(1, entity.artisanId)
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.locationText)
        statement.bindDouble(4, entity.latitude)
        statement.bindDouble(5, entity.longitude)
        statement.bindLong(6, entity.experienceYears.toLong())
        statement.bindText(7, entity.craftType)
        statement.bindText(8, entity.bio)
        statement.bindText(9, entity.photoUrl)
      }
    }
  }

  public override suspend fun insertAll(artisans: List<Artisan>): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfArtisan.insert(_connection, artisans)
  }

  public override fun getAllArtisans(): Flow<List<Artisan>> {
    val _sql: String = "SELECT * FROM artisans"
    return createFlow(__db, false, arrayOf("artisans")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfArtisanId: Int = getColumnIndexOrThrow(_stmt, "artisanId")
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfLocationText: Int = getColumnIndexOrThrow(_stmt, "locationText")
        val _cursorIndexOfLatitude: Int = getColumnIndexOrThrow(_stmt, "latitude")
        val _cursorIndexOfLongitude: Int = getColumnIndexOrThrow(_stmt, "longitude")
        val _cursorIndexOfExperienceYears: Int = getColumnIndexOrThrow(_stmt, "experienceYears")
        val _cursorIndexOfCraftType: Int = getColumnIndexOrThrow(_stmt, "craftType")
        val _cursorIndexOfBio: Int = getColumnIndexOrThrow(_stmt, "bio")
        val _cursorIndexOfPhotoUrl: Int = getColumnIndexOrThrow(_stmt, "photoUrl")
        val _result: MutableList<Artisan> = mutableListOf()
        while (_stmt.step()) {
          val _item: Artisan
          val _tmpArtisanId: String
          _tmpArtisanId = _stmt.getText(_cursorIndexOfArtisanId)
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpLocationText: String
          _tmpLocationText = _stmt.getText(_cursorIndexOfLocationText)
          val _tmpLatitude: Double
          _tmpLatitude = _stmt.getDouble(_cursorIndexOfLatitude)
          val _tmpLongitude: Double
          _tmpLongitude = _stmt.getDouble(_cursorIndexOfLongitude)
          val _tmpExperienceYears: Int
          _tmpExperienceYears = _stmt.getLong(_cursorIndexOfExperienceYears).toInt()
          val _tmpCraftType: String
          _tmpCraftType = _stmt.getText(_cursorIndexOfCraftType)
          val _tmpBio: String
          _tmpBio = _stmt.getText(_cursorIndexOfBio)
          val _tmpPhotoUrl: String
          _tmpPhotoUrl = _stmt.getText(_cursorIndexOfPhotoUrl)
          _item =
              Artisan(_tmpArtisanId,_tmpName,_tmpLocationText,_tmpLatitude,_tmpLongitude,_tmpExperienceYears,_tmpCraftType,_tmpBio,_tmpPhotoUrl)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getArtisanById(id: String): Artisan? {
    val _sql: String = "SELECT * FROM artisans WHERE artisanId = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        val _cursorIndexOfArtisanId: Int = getColumnIndexOrThrow(_stmt, "artisanId")
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfLocationText: Int = getColumnIndexOrThrow(_stmt, "locationText")
        val _cursorIndexOfLatitude: Int = getColumnIndexOrThrow(_stmt, "latitude")
        val _cursorIndexOfLongitude: Int = getColumnIndexOrThrow(_stmt, "longitude")
        val _cursorIndexOfExperienceYears: Int = getColumnIndexOrThrow(_stmt, "experienceYears")
        val _cursorIndexOfCraftType: Int = getColumnIndexOrThrow(_stmt, "craftType")
        val _cursorIndexOfBio: Int = getColumnIndexOrThrow(_stmt, "bio")
        val _cursorIndexOfPhotoUrl: Int = getColumnIndexOrThrow(_stmt, "photoUrl")
        val _result: Artisan?
        if (_stmt.step()) {
          val _tmpArtisanId: String
          _tmpArtisanId = _stmt.getText(_cursorIndexOfArtisanId)
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpLocationText: String
          _tmpLocationText = _stmt.getText(_cursorIndexOfLocationText)
          val _tmpLatitude: Double
          _tmpLatitude = _stmt.getDouble(_cursorIndexOfLatitude)
          val _tmpLongitude: Double
          _tmpLongitude = _stmt.getDouble(_cursorIndexOfLongitude)
          val _tmpExperienceYears: Int
          _tmpExperienceYears = _stmt.getLong(_cursorIndexOfExperienceYears).toInt()
          val _tmpCraftType: String
          _tmpCraftType = _stmt.getText(_cursorIndexOfCraftType)
          val _tmpBio: String
          _tmpBio = _stmt.getText(_cursorIndexOfBio)
          val _tmpPhotoUrl: String
          _tmpPhotoUrl = _stmt.getText(_cursorIndexOfPhotoUrl)
          _result =
              Artisan(_tmpArtisanId,_tmpName,_tmpLocationText,_tmpLatitude,_tmpLongitude,_tmpExperienceYears,_tmpCraftType,_tmpBio,_tmpPhotoUrl)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun count(): Int {
    val _sql: String = "SELECT COUNT(*) FROM artisans"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: Int
        if (_stmt.step()) {
          val _tmp: Int
          _tmp = _stmt.getLong(0).toInt()
          _result = _tmp
        } else {
          _result = 0
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
