package com.channapatna.nammapride.`data`.local.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.channapatna.nammapride.`data`.local.entity.Toy
import javax.`annotation`.processing.Generated
import kotlin.Boolean
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
public class ToyDao_Impl(
  __db: RoomDatabase,
) : ToyDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfToy: EntityInsertAdapter<Toy>
  init {
    this.__db = __db
    this.__insertAdapterOfToy = object : EntityInsertAdapter<Toy>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `toys` (`toyId`,`name`,`artisanId`,`material`,`processDescription`,`imageUrl`,`isAuthentic`,`verificationCode`,`price`,`category`,`isFavorite`) VALUES (?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: Toy) {
        statement.bindText(1, entity.toyId)
        statement.bindText(2, entity.name)
        statement.bindText(3, entity.artisanId)
        statement.bindText(4, entity.material)
        statement.bindText(5, entity.processDescription)
        statement.bindText(6, entity.imageUrl)
        val _tmp: Int = if (entity.isAuthentic) 1 else 0
        statement.bindLong(7, _tmp.toLong())
        statement.bindText(8, entity.verificationCode)
        statement.bindText(9, entity.price)
        statement.bindText(10, entity.category)
        val _tmp_1: Int = if (entity.isFavorite) 1 else 0
        statement.bindLong(11, _tmp_1.toLong())
      }
    }
  }

  public override suspend fun insertAll(toys: List<Toy>): Unit = performSuspending(__db, false,
      true) { _connection ->
    __insertAdapterOfToy.insert(_connection, toys)
  }

  public override fun getAllToys(): Flow<List<Toy>> {
    val _sql: String = "SELECT * FROM toys"
    return createFlow(__db, false, arrayOf("toys")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfToyId: Int = getColumnIndexOrThrow(_stmt, "toyId")
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfArtisanId: Int = getColumnIndexOrThrow(_stmt, "artisanId")
        val _cursorIndexOfMaterial: Int = getColumnIndexOrThrow(_stmt, "material")
        val _cursorIndexOfProcessDescription: Int = getColumnIndexOrThrow(_stmt,
            "processDescription")
        val _cursorIndexOfImageUrl: Int = getColumnIndexOrThrow(_stmt, "imageUrl")
        val _cursorIndexOfIsAuthentic: Int = getColumnIndexOrThrow(_stmt, "isAuthentic")
        val _cursorIndexOfVerificationCode: Int = getColumnIndexOrThrow(_stmt, "verificationCode")
        val _cursorIndexOfPrice: Int = getColumnIndexOrThrow(_stmt, "price")
        val _cursorIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _cursorIndexOfIsFavorite: Int = getColumnIndexOrThrow(_stmt, "isFavorite")
        val _result: MutableList<Toy> = mutableListOf()
        while (_stmt.step()) {
          val _item: Toy
          val _tmpToyId: String
          _tmpToyId = _stmt.getText(_cursorIndexOfToyId)
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpArtisanId: String
          _tmpArtisanId = _stmt.getText(_cursorIndexOfArtisanId)
          val _tmpMaterial: String
          _tmpMaterial = _stmt.getText(_cursorIndexOfMaterial)
          val _tmpProcessDescription: String
          _tmpProcessDescription = _stmt.getText(_cursorIndexOfProcessDescription)
          val _tmpImageUrl: String
          _tmpImageUrl = _stmt.getText(_cursorIndexOfImageUrl)
          val _tmpIsAuthentic: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_cursorIndexOfIsAuthentic).toInt()
          _tmpIsAuthentic = _tmp != 0
          val _tmpVerificationCode: String
          _tmpVerificationCode = _stmt.getText(_cursorIndexOfVerificationCode)
          val _tmpPrice: String
          _tmpPrice = _stmt.getText(_cursorIndexOfPrice)
          val _tmpCategory: String
          _tmpCategory = _stmt.getText(_cursorIndexOfCategory)
          val _tmpIsFavorite: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_cursorIndexOfIsFavorite).toInt()
          _tmpIsFavorite = _tmp_1 != 0
          _item =
              Toy(_tmpToyId,_tmpName,_tmpArtisanId,_tmpMaterial,_tmpProcessDescription,_tmpImageUrl,_tmpIsAuthentic,_tmpVerificationCode,_tmpPrice,_tmpCategory,_tmpIsFavorite)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getToyById(id: String): Toy? {
    val _sql: String = "SELECT * FROM toys WHERE toyId = ? OR verificationCode = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindText(_argIndex, id)
        _argIndex = 2
        _stmt.bindText(_argIndex, id)
        val _cursorIndexOfToyId: Int = getColumnIndexOrThrow(_stmt, "toyId")
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfArtisanId: Int = getColumnIndexOrThrow(_stmt, "artisanId")
        val _cursorIndexOfMaterial: Int = getColumnIndexOrThrow(_stmt, "material")
        val _cursorIndexOfProcessDescription: Int = getColumnIndexOrThrow(_stmt,
            "processDescription")
        val _cursorIndexOfImageUrl: Int = getColumnIndexOrThrow(_stmt, "imageUrl")
        val _cursorIndexOfIsAuthentic: Int = getColumnIndexOrThrow(_stmt, "isAuthentic")
        val _cursorIndexOfVerificationCode: Int = getColumnIndexOrThrow(_stmt, "verificationCode")
        val _cursorIndexOfPrice: Int = getColumnIndexOrThrow(_stmt, "price")
        val _cursorIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _cursorIndexOfIsFavorite: Int = getColumnIndexOrThrow(_stmt, "isFavorite")
        val _result: Toy?
        if (_stmt.step()) {
          val _tmpToyId: String
          _tmpToyId = _stmt.getText(_cursorIndexOfToyId)
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpArtisanId: String
          _tmpArtisanId = _stmt.getText(_cursorIndexOfArtisanId)
          val _tmpMaterial: String
          _tmpMaterial = _stmt.getText(_cursorIndexOfMaterial)
          val _tmpProcessDescription: String
          _tmpProcessDescription = _stmt.getText(_cursorIndexOfProcessDescription)
          val _tmpImageUrl: String
          _tmpImageUrl = _stmt.getText(_cursorIndexOfImageUrl)
          val _tmpIsAuthentic: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_cursorIndexOfIsAuthentic).toInt()
          _tmpIsAuthentic = _tmp != 0
          val _tmpVerificationCode: String
          _tmpVerificationCode = _stmt.getText(_cursorIndexOfVerificationCode)
          val _tmpPrice: String
          _tmpPrice = _stmt.getText(_cursorIndexOfPrice)
          val _tmpCategory: String
          _tmpCategory = _stmt.getText(_cursorIndexOfCategory)
          val _tmpIsFavorite: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_cursorIndexOfIsFavorite).toInt()
          _tmpIsFavorite = _tmp_1 != 0
          _result =
              Toy(_tmpToyId,_tmpName,_tmpArtisanId,_tmpMaterial,_tmpProcessDescription,_tmpImageUrl,_tmpIsAuthentic,_tmpVerificationCode,_tmpPrice,_tmpCategory,_tmpIsFavorite)
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
    val _sql: String = "SELECT COUNT(*) FROM toys"
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

  public override fun getFavoriteToys(): Flow<List<Toy>> {
    val _sql: String = "SELECT * FROM toys WHERE isFavorite = 1"
    return createFlow(__db, false, arrayOf("toys")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _cursorIndexOfToyId: Int = getColumnIndexOrThrow(_stmt, "toyId")
        val _cursorIndexOfName: Int = getColumnIndexOrThrow(_stmt, "name")
        val _cursorIndexOfArtisanId: Int = getColumnIndexOrThrow(_stmt, "artisanId")
        val _cursorIndexOfMaterial: Int = getColumnIndexOrThrow(_stmt, "material")
        val _cursorIndexOfProcessDescription: Int = getColumnIndexOrThrow(_stmt,
            "processDescription")
        val _cursorIndexOfImageUrl: Int = getColumnIndexOrThrow(_stmt, "imageUrl")
        val _cursorIndexOfIsAuthentic: Int = getColumnIndexOrThrow(_stmt, "isAuthentic")
        val _cursorIndexOfVerificationCode: Int = getColumnIndexOrThrow(_stmt, "verificationCode")
        val _cursorIndexOfPrice: Int = getColumnIndexOrThrow(_stmt, "price")
        val _cursorIndexOfCategory: Int = getColumnIndexOrThrow(_stmt, "category")
        val _cursorIndexOfIsFavorite: Int = getColumnIndexOrThrow(_stmt, "isFavorite")
        val _result: MutableList<Toy> = mutableListOf()
        while (_stmt.step()) {
          val _item: Toy
          val _tmpToyId: String
          _tmpToyId = _stmt.getText(_cursorIndexOfToyId)
          val _tmpName: String
          _tmpName = _stmt.getText(_cursorIndexOfName)
          val _tmpArtisanId: String
          _tmpArtisanId = _stmt.getText(_cursorIndexOfArtisanId)
          val _tmpMaterial: String
          _tmpMaterial = _stmt.getText(_cursorIndexOfMaterial)
          val _tmpProcessDescription: String
          _tmpProcessDescription = _stmt.getText(_cursorIndexOfProcessDescription)
          val _tmpImageUrl: String
          _tmpImageUrl = _stmt.getText(_cursorIndexOfImageUrl)
          val _tmpIsAuthentic: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_cursorIndexOfIsAuthentic).toInt()
          _tmpIsAuthentic = _tmp != 0
          val _tmpVerificationCode: String
          _tmpVerificationCode = _stmt.getText(_cursorIndexOfVerificationCode)
          val _tmpPrice: String
          _tmpPrice = _stmt.getText(_cursorIndexOfPrice)
          val _tmpCategory: String
          _tmpCategory = _stmt.getText(_cursorIndexOfCategory)
          val _tmpIsFavorite: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_cursorIndexOfIsFavorite).toInt()
          _tmpIsFavorite = _tmp_1 != 0
          _item =
              Toy(_tmpToyId,_tmpName,_tmpArtisanId,_tmpMaterial,_tmpProcessDescription,_tmpImageUrl,_tmpIsAuthentic,_tmpVerificationCode,_tmpPrice,_tmpCategory,_tmpIsFavorite)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getFavoriteIds(): List<String> {
    val _sql: String = "SELECT toyId FROM toys WHERE isFavorite = 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _result: MutableList<String> = mutableListOf()
        while (_stmt.step()) {
          val _item: String
          _item = _stmt.getText(0)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateFavorite(id: String, isFav: Boolean) {
    val _sql: String = "UPDATE toys SET isFavorite = ? WHERE toyId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        val _tmp: Int = if (isFav) 1 else 0
        _stmt.bindLong(_argIndex, _tmp.toLong())
        _argIndex = 2
        _stmt.bindText(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
