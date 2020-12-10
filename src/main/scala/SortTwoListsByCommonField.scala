import java.util.Date
import scala.language.reflectiveCalls

case class TestDrive(car: String, salesPerson: String, date: Date)

case class Purchase(car: String, salesPerson: String, soldPrice: Int, date: Date)

object SortTwoListsByCommonField {
  def main(args: Array[String]): Unit = {
    val date = new Date
    val dateFormat = new java.text.SimpleDateFormat("yyyy/MM/dd HH:mm:ss")

    val testDrives = List(
      TestDrive("Charger", "John Smith", new Date(date.getTime - 1000000)),
      TestDrive("Camero", "Jane Doe", new Date(date.getTime - 4000000)),
      TestDrive("Mustang", "Frank Smith", new Date(date.getTime - 10000000))
    )

    val purchases = List(
      Purchase("G35x", "Jane Doe", 19500, new Date(date.getTime - 500000)),
      Purchase("Altima", "Jane Doe", 28500, new Date(date.getTime - 6000000)),
      Purchase("Maxima", "Frank Smith", 35000, new Date(date.getTime - 9000000))
    )

    // Combine the TestDrives and Purchases lists and sort descending by date
    val activities = (testDrives ::: purchases).asInstanceOf[List[ {def date: Date}]].sortBy(_.date).reverse

    // Now if we want to show fields other than "date", we can use pattern matching to cast each activity
    for (activity <- activities) {
      activity match {
        case t: TestDrive =>
          println("%s\t|\t%s\t|\t%s\t|\t%s"
            .format(dateFormat.format(t.date), t.getClass.getSimpleName, t.car, t.salesPerson))
        case p: Purchase =>
          println("%s\t|\t%s\t|\t%s\t|\t%s\t|\t$%s"
            .format(dateFormat.format(p.date), p.getClass.getSimpleName, p.car, p.salesPerson, p.soldPrice))
      }
    }
  }
}

