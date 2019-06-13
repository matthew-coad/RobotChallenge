/**
 * Represents a direction the robot can face.
 * 
 * As well as the direction names contains the coordinate deltas for robot moves
 *  
 */
sealed abstract class Facing(val name: String, val deltaX: Int, val deltaY: Int) {
  override def toString() = name
  
  /**
   * Get the facing to the left 
   */
  def left(): Facing = this match {
    case Facing.North => Facing.West
    case Facing.West => Facing.South
    case Facing.South => Facing.East
    case Facing.East => Facing.North
  }
  
  /**
   * Get the facing to the right 
   */
  def right(): Facing = this match {
    case Facing.North => Facing.East
    case Facing.East => Facing.South
    case Facing.South => Facing.West
    case Facing.West => Facing.North
  }  

}

object Facing {
  
  // Case objects that represent the valid facings
  case object North extends Facing("NORTH", 0, 1)
  case object South extends Facing("SOUTH", 0, -1)
  case object East extends Facing("EAST", 1, 0)
  case object West extends Facing("WEST", -1, 0)
  
  /**
   * Given a text string, return a matching facing. 
   */
  def parse(input: String): Facing =
    input match {
      case North.name => North
      case South.name => South
      case East.name => East
      case West.name => West
      case _ => throw new RuntimeException("Invalid facing")
    }
  
}
