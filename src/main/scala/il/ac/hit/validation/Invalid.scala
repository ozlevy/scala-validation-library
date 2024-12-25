package il.ac.hit.validation

/**
 * Represents a failed validation result.
 *
 * @param reason the reason for validation failure
 */
class Invalid(reason: String) extends ValidationResult {
  override def isValid: Boolean = false
  override def getReason: Option[String] = Some(reason)
}
