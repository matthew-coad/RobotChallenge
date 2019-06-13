
/**
 * Immutable robot state at a point in time.
 * 
 * x - Robot x coordinate
 * y - Robot y coordinate
 * facing - Direction the robot is facing
 * placed - Has the robot been placed on the board
 *  
 */
case class Robot(x:Int, y:Int, facing:Facing, placed:Boolean) {

  /**
   * Place the robot at a location on the board.
   * 
   * x - New x coordinate 
   * y - New y coordinate
   * facing - New facing
   * 
   * returns - New robot state. If the new position is not valid, it returns 
   * the current state.
   *   
   */
  def place(x:Int, y:Int, facing:Facing): Robot = {
    val next = Robot(x, y, facing, true)
    if (!next.position_valid())
      return this
    return next
  }
  
  /**
   * Is the position of the robot valid?
   * 
   * Can only be true for robot states created temporarily as
   * part of a move or place.
   *  
   */
  def position_valid(): Boolean =
    this.x >= Board.Left && this.x <= Board.Right && this.y >= Board.Bottom && this.y <= Board.Top

  /**
   * Move the robot forward one step if its been placed.
   * 
   * Leaves the robot unchanged if the new position is invalid.
   * 
   */
  def move(): Robot = {
    if (!this.placed)
      return this
    val next = new Robot(
      this.x + this.facing.deltaX, 
      this.y + this.facing.deltaY, 
      this.facing,
      true)
    if (!next.position_valid())
      return this
    return next
  }
    
  /**
   * Turn the robot left if its been placed
   * 
   */
  def left(): Robot = {
    if (!this.placed)
      return this
    return new Robot(
      this.x, 
      this.y, 
      this.facing.left(),
      true)
  }

  /**
   * Turn the robot right if its been placed
   */
  def right(): Robot = {
    if (!this.placed)
      return this
    return new Robot(
      this.x, 
      this.y, 
      this.facing.right(),
      true)
  }
  
  /**
   * Generate a report on the state of the robot
   */
  def report() =
    if (this.placed) 
      s"${this.x},${this.y},${this.facing}" 
    else
      "UNPLACED"
      
  override def toString() = report()
 
}

object Robot {
  
  /** Make a new robot **/
  def make() = new Robot(0,0,Facing.North, false)
  
}
