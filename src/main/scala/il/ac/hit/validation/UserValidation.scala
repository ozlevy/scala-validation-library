package il.ac.hit.validation

/**
 * Trait for user validation logic.
 * Allows combining validation rules with AND/OR logic.
 */
trait UserValidation extends (User => ValidationResult) {

  /** Combines two validations with AND logic. */
  def and(that: UserValidation): UserValidation = (user: User) =>
    if (!this.apply(user).isValid) this.apply(user) else that.apply(user)

  /** Combines two validations with OR logic. */
  def or(that: UserValidation): UserValidation = (user: User) =>
    if (this.apply(user).isValid) this.apply(user) else that.apply(user)
}

object UserValidation {

  /**
   * Combines multiple validations with AND logic.
   * @param validations the validations to combine
   * @return a new validation combining all
   */
  def all(validations: UserValidation*): UserValidation = (user: User) =>
    validations.map(_.apply(user)).find(!_.isValid).getOrElse(new Valid)

  /**
   * Combines multiple validations with OR logic.
   * @param validations the validations to combine
   * @return a new validation combining any
   */
  def none(validations: UserValidation*): UserValidation = (user: User) =>
    if (validations.exists(_.apply(user).isValid)) new Invalid("One condition was valid") else new Valid

  /** Validation: Email ends with ".il" */
  def emailEndsWithIL: UserValidation = (user: User) =>
    try {
      if (user.email.endsWith("il")) new Valid
      else new Invalid("Email does not end with 'il'")
    } catch {
      case ex: Exception =>
        new Invalid(s"Unexpected error in email validation: ${ex.getMessage}")
    }

  /** Validation: Email length must be greater than 10. */
  def emailLengthBiggerThan10: UserValidation = (user: User) =>
    if (user.email.length > 10) new Valid else new Invalid("Email length <= 10")

  /** Validation: Password length must be greater than 8. */
  def passwordLengthBiggerThan8: UserValidation = (user: User) =>
    if (user.password.length > 8) new Valid else new Invalid("Password length <= 8")

  /** Validation: Password must include only letters and numbers. */
  def passwordIncludesLettersNumbersOnly: UserValidation = (user: User) =>
    if (user.password.matches("^[a-zA-Z0-9]+$")) new Valid else new Invalid("Password must include only letters and numbers")

  /** Validation: Password must include a '$' character. */
  def passwordIncludesDollarSign: UserValidation = (user: User) =>
    if (user.password.contains("$")) new Valid else new Invalid("Password does not include $ sign")

  /** Validation: Password must be different from the username. */
  def passwordIsDifferentFromUsername: UserValidation = (user: User) =>
    if (user.password != user.username) new Valid else new Invalid("Password is the same as username")

  /** Validation: Age must be greater than 18. */
  def ageBiggerThan18: UserValidation = (user: User) =>
    try {
      if (user.age > 18) new Valid
      else new Invalid("User is under 18")
    } catch {
      case ex: Exception =>
        new Invalid(s"Unexpected error in age validation: ${ex.getMessage}")
    }

  /** Validation: Username length must be greater than 8. */
  def usernameLengthBiggerThan8: UserValidation = (user: User) =>
    if (user.username.length > 8) new Valid else new Invalid("Username length <= 8")
}
