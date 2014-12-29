/**
 * Created by jhaddad on 12/29/14.
 *
 */


import org.apache.spark.{SparkContext,SparkConf}

import com.datastax.spark.connector._

object DataMigration {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf(true)
      .set("spark.cassandra.connection.host", "127.0.0.1").setMaster("local")

    val sc = new SparkContext("local", "test", conf)

    case class FoodToUserIndex(food: String, name: String)

    val user_table = sc.cassandraTable("tutorial", "user")

    val food_index = user_table.map(r => new FoodToUserIndex(r.getString("favorite_food"), r.getString("user")))

    food_index.saveToCassandra("tutorial", "food_to_user_index")

  }

}
