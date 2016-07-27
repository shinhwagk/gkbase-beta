```scala
val jsonString:String = ...

case class SourceInsertSql(mode: String, db: String, sql: String)

implicit val personWrites: Reads[SourceInsertSql] = Json.reads[SourceInsertSql]

val obj:SourceInsertSql = Json.parse(jsonString).as[SourceInsertSql]
```
