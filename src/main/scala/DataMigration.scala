/**
 * Created by jhaddad on 12/29/14.
 *
 */

import org.apache.spark.{SparkContext,SparkConf}

import com.datastax.spark.connector._

object DataMigration {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf(true)
      .set("spark.cassandra.connection.host", "127.0.0.1")

    val sc = new SparkContext("local", "test", conf)

    case class FoodToUserIndex(food: String, user: String)

    val user_table = sc.cassandraTable("tutorial", "user")

    val food_index = user_table.map(r => new FoodToUserIndex(r.getString("favorite_food"), r.getString("name")))

    food_index.saveToCassandra("tutorial", "food_to_user_index")

  }

}
