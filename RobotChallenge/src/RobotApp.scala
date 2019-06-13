import java.nio.file.Paths

object RobotApp {
  
  def main(args: Array[String]): Unit = {
    
    println("===== REA Robot =====")
    
    if (args.length == 0) {
      println("Program file not specified")
      return
    }
    val filename = args(0)
    if (!new java.io.File(filename).exists) {
      println(s"${filename} not found")
      return
      
    }
    println(s"--- Running '$filename' ---")
    val program = scala.io.Source.fromFile(filename).getLines().toArray
    println(program.mkString("\n"))
        
    println("--- Output ---")

    var simulation = Simulation.run(program)
    println(simulation.messages.mkString("\n"))
    
    println("--- Complete ---")
  }
  
}
