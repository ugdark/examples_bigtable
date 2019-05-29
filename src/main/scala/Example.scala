import com.google.cloud.bigtable.hbase.BigtableConfiguration
import java.io.IOException
import org.apache.hadoop.hbase.TableName
import org.apache.hadoop.hbase.client.Get
import org.apache.hadoop.hbase.util.Bytes


object Example {

  def main(args: Array[String]): Unit = {

    val projectId = "" // my-gcp-project-id
    val instanceId = "quickstart-instance" // my-bigtable-instance-id
    val tableId = "my-table" // my-bigtable-table-id

    // Create a connection to the Cloud Bigtable instance.
    // Use try-with-resources to make sure the connection is closed correctly
    try {
      val connection = BigtableConfiguration.connect(projectId, instanceId)
      try {
        println("--- Connection established with Bigtable Instance ---")
        // Create a connection to the table that already exists
        // Use try-with-resources to make sure the connection to the table is closed correctly
        try {
          val table = connection.getTable(TableName.valueOf(tableId))
          try { // Read a row
            val rowKey = "r1"
            printf("--- Reading for row-key: %s for provided table: %s ---\n", rowKey, tableId)
            // Retrieve the result
            val result = table.get(new Get(Bytes.toBytes(rowKey)))
            // Convert row data to string
            val rowValue = Bytes.toString(result.value)
            printf("Scanned value for Row r1: %s \n", rowValue)
            println(" --- Finished reading row --- ")
          } catch {
            case e: IOException =>
              // handle exception while connecting to a table
              throw e
          } finally if (table != null) table.close()
        }
      } catch {
        case e: IOException =>
          System.err.println("Exception while running quickstart: " + e.getMessage)
          e.printStackTrace()
      } finally if (connection != null) connection.close()
    }
  }
}
