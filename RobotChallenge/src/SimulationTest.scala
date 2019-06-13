import org.junit.Test
import org.junit.Assert._

class SimulationTest {

  //### Example a
  //
  //    PLACE 0,0,NORTH
  //    MOVE
  //    REPORT
  //
  //Expected output:
  //
  //    0,1,NORTH
  @Test def exampleA() {
    val program = Array(
        "PLACE 0,0,NORTH",
        "MOVE",
        "REPORT"
    )
    val simulation = Simulation.run(program)
    assertEquals(simulation.messages(0), "0,1,NORTH")
  }
  
  //### Example b
  //
  //    PLACE 0,0,NORTH
  //    LEFT
  //    REPORT
  //
  //Expected output:
  //
  //    0,0,WEST
  //
  @Test def exampleB() {
    val program = Array(
        "PLACE 0,0,NORTH",
        "LEFT",
        "REPORT"
    )
    val simulation = Simulation.run(program)
    assertEquals(simulation.messages(0), "0,0,WEST")
  }
  
  //### Example c
  //
  //    PLACE 1,2,EAST
  //    MOVE
  //    MOVE
  //    LEFT
  //    MOVE
  //    REPORT
  //
  //Expected output
  //
  //    3,3,NORTH
  //
  @Test def exampleC() {
    val program = Array(
        "PLACE 1,2,EAST",
        "MOVE",
        "MOVE",
        "LEFT",
        "MOVE",
        "REPORT"
     )
    val simulation = Simulation.run(program)
    assertEquals(simulation.messages(0), "3,3,NORTH")
  }
  
  // Living on the edge!
  //
  // Walk along the map edges, attempting to push of at each border
  @Test def livingOnTheEdgeC() {
    val program = Array(
        "PLACE 0,0,NORTH",
        "MOVE",
        "MOVE",
        "MOVE",
        "MOVE",
        "MOVE",
        "MOVE",
        "REPORT",
        "RIGHT",
        "MOVE",
        "MOVE",
        "MOVE",
        "MOVE",
        "MOVE",
        "MOVE",
        "REPORT",
        "RIGHT",
        "MOVE",
        "MOVE",
        "MOVE",
        "MOVE",
        "MOVE",
        "MOVE",
        "REPORT",
        "RIGHT",
        "MOVE",
        "MOVE",
        "MOVE",
        "MOVE",
        "MOVE",
        "MOVE",
        "REPORT"
     )
    val simulation = Simulation.run(program)
    assertEquals(simulation.messages(0), "0,4,NORTH")
    assertEquals(simulation.messages(1), "4,4,EAST")
    assertEquals(simulation.messages(2), "4,0,SOUTH")
    assertEquals(simulation.messages(3), "0,0,WEST")
  }
  
  // Living on the left edge!
  //
  // Walk along the map edges, attempting to push of at each border
  // but by turning left
  @Test def livingOnTheLeftEdge() {
    val program = Array(
        "PLACE 0,0,EAST",
        "MOVE",
        "MOVE",
        "MOVE",
        "MOVE",
        "MOVE",
        "MOVE",
        "REPORT",
        "LEFT",
        "MOVE",
        "MOVE",
        "MOVE",
        "MOVE",
        "MOVE",
        "MOVE",
        "REPORT",
        "LEFT",
        "MOVE",
        "MOVE",
        "MOVE",
        "MOVE",
        "MOVE",
        "MOVE",
        "REPORT",
        "LEFT",
        "MOVE",
        "MOVE",
        "MOVE",
        "MOVE",
        "MOVE",
        "MOVE",
        "REPORT"
     )
    val simulation = Simulation.run(program)
    assertEquals(simulation.messages(0), "4,0,EAST")
    assertEquals(simulation.messages(1), "4,4,NORTH")
    assertEquals(simulation.messages(2), "0,4,WEST")
    assertEquals(simulation.messages(3), "0,0,SOUTH")
  }
  
  // You spin me right round!
  //
  // Turn right 4 times. Should get back to north.
  @Test def spinMeRightRound() {
    val program = Array(
        "PLACE 1,2,NORTH",
        "RIGHT",
        "RIGHT",
        "RIGHT",
        "RIGHT",
        "REPORT"
     )
    val simulation = Simulation.run(program)
    assertEquals(simulation.messages(0), "1,2,NORTH")
  }

  // You spin me left round!
  //
  // Turn left 4 times. Should get back to east.
  @Test def spinMeLeftRound() {
    val program = Array(
        "PLACE 1,2,EAST",
        "LEFT",
        "LEFT",
        "LEFT",
        "LEFT",
        "REPORT"
     )
    val simulation = Simulation.run(program)
    assertEquals(simulation.messages(0), "1,2,EAST")
  }
  
  // I'm not ready yet!
  //
  // Unplaced robots ignore the report command
  @Test def notReadyYet() {
    val program = Array(
        "REPORT"
     )
    val simulation = Simulation.run(program)
    assertEquals(0, simulation.messages.length)
  }
  
  // I'm still not ready yet!
  //
  // Unplaced robots ignore all move commands
  @Test def stillNotReadyYet() {
    val program = Array(
        "MOVE",
        "LEFT",
        "RIGHT",
        "REPORT"
     )
    val simulation = Simulation.run(program)
    assertEquals(0, simulation.messages.length)
  }
  
  // Here we go again
  //
  // Placing a robot again moves it to the new position
  @Test def hereWeGoAgain() {
    val program = Array(
        "PLACE 1,2,EAST",
        "REPORT",
        "PLACE 4,4,SOUTH",
        "REPORT"

     )
    val simulation = Simulation.run(program)
    assertEquals("1,2,EAST", simulation.messages(0))
    assertEquals("4,4,SOUTH", simulation.messages(1))
  }
  
  // why walk when you can teleport?
  //
  // Place the robot in each cardinal direction to test
  // direction parsing
  @Test def whyWalkWhenTeleport() {
    val program = Array(
        "PLACE 0,0,EAST",
        "REPORT",
        "PLACE 4,4,SOUTH",
        "REPORT",
        "PLACE 4,0,NORTH",
        "REPORT",
        "PLACE 2,2,WEST",
        "REPORT"
     )
    val simulation = Simulation.run(program)
    assertEquals("0,0,EAST", simulation.messages(0))
    assertEquals("4,4,SOUTH", simulation.messages(1))
    assertEquals("4,0,NORTH", simulation.messages(2))
    assertEquals("2,2,WEST", simulation.messages(3))
  }
  
  // You want me to go where?
  //
  // Robot ignores attempts to place it off the board
  @Test def youWantMeToGoWhere() {
    
    // **NOTE** Can't place at negate coords as its an invalid command
    val program = Array(
        "PLACE 5,2,SOUTH",
        "REPORT",
        "PLACE 2,5,NORTH",
        "REPORT"
     )
    val simulation = Simulation.run(program)
    assertEquals(0, simulation.messages.length)
  }
  


}