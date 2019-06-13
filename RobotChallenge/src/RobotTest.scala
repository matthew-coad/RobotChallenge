import org.junit.Test
import org.junit.Assert._

class RobotTest {
  
  @Test def validPlace() {
    val robot = Robot.make().place(1, 2, Facing.South)
    assertTrue(robot.placed)
    assertEquals(robot.x, 1)
    assertEquals(robot.y, 2)
    assertEquals(robot.facing, Facing.South)
  }
  
  @Test def invalidPlace() {
    var robot = Robot.make().place(-1, 2, Facing.South)
    assertFalse(robot.placed)
    robot = Robot.make().place(5, 2, Facing.South)
    assertFalse(robot.placed)
    robot = Robot.make().place(1, -2, Facing.South)
    assertFalse(robot.placed)
    robot = Robot.make().place(1, 6, Facing.South)
    assertFalse(robot.placed)
  }
  
  @Test def moveNorth() {
    val robot = Robot.make().place(1,2, Facing.North).move()
    assertEquals(1, robot.x)
    assertEquals(3, robot.y)
    assertEquals(robot.facing, Facing.North)
    
    val blocked = Robot.make().place(1,4, Facing.North).move()
    assertEquals(4, blocked.y)
  }

  @Test def moveSouth() {
    val robot = Robot.make().place(1,2, Facing.South).move()
    assertEquals(1, robot.x)
    assertEquals(1, robot.y)
    
    val blocked = Robot.make().place(1,0, Facing.South).move()
    assertEquals(0, blocked.y)
  }

  @Test def moveWest() {
    val robot = Robot.make().place(1, 2, Facing.West).move()
    assertEquals(0, robot.x)
    assertEquals(2, robot.y)
    
    val blocked = Robot.make().place(0, 2, Facing.West).move()
    assertEquals(0, blocked.x)
  }

  @Test def moveEast() {
    val robot = Robot.make().place(1, 2, Facing.East).move()
    assertEquals(2, robot.x)
    assertEquals(2, robot.y)
    
    val blocked = Robot.make().place(4, 2, Facing.East).move()
    assertEquals(4, blocked.x)
  }
  
  @Test def turnLeft() {
    var robot = Robot.make().place(1, 2, Facing.North)
    assertEquals(Facing.North, robot.facing)
    assertNotEquals(Facing.South, robot.facing)
    robot = robot.left()
    assertEquals(Facing.West, robot.facing)
    robot = robot.left()
    assertEquals(Facing.South, robot.facing)
    robot = robot.left()
    assertEquals(Facing.East, robot.facing)
    robot = robot.left()
    assertEquals(Facing.North, robot.facing)
  }

  @Test def turnRight() {
    var robot = Robot.make().place(1, 2, Facing.North)
    assertEquals(Facing.North, robot.facing)
    robot = robot.right()
    assertEquals(Facing.East, robot.facing)
    robot = robot.right()
    assertEquals(Facing.South, robot.facing)
    robot = robot.right()
    assertEquals(Facing.West, robot.facing)
    robot = robot.right()
    assertEquals(Facing.North, robot.facing)
  }

  @Test def reportState() {
    var robot = Robot.make().place(1, 2, Facing.North)
    var report = robot.report() 
    assertEquals("1,2,NORTH", report )
    robot = Robot.make().place(3, 4, Facing.East)
    report = robot.report()
    assertEquals("3,4,EAST", report )
    robot = Robot.make()
    report = robot.report()
    assertEquals("UNPLACED", report )

  }

}