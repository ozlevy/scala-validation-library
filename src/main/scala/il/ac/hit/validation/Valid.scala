package il.ac.hit.validation

/**
 * Represents a successful validation result.
 */
class Valid extends ValidationResult {
  override def isValid: Boolean = true
  override def getReason: Option[String] = None
}
