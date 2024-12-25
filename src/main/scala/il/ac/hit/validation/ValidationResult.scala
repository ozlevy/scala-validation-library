package il.ac.hit.validation

/**
 * Trait for the result of a validation.
 * Includes methods to check validity and retrieve the failure reason.
 */
trait ValidationResult {
  /**
   * Checks if the validation passed.
   * @return true if valid, false otherwise
   */
  def isValid: Boolean

  /**
   * Retrieves the reason for validation failure, if any.
   * @return an Option containing the failure reason, or None if valid
   */
  def getReason: Option[String]
}
