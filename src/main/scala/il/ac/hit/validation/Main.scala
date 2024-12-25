package il.ac.hit.validation

object Main {

  def main(args: Array[String]): Unit = {

    val user1: User = new User("haim", "haim.michael@gmail.il", "abc123", 15)

    val result1: ValidationResult = UserValidation.emailEndsWithIL.
      and(UserValidation.emailLengthBiggerThan10).apply(user1);
    print(result1.isValid);

    val result2: ValidationResult = UserValidation.emailEndsWithIL.
      or(UserValidation.emailLengthBiggerThan10).apply(user1);
    print(result2.isValid);

    val validations = UserValidation.all(
      UserValidation.emailEndsWithIL,
      UserValidation.passwordLengthBiggerThan8,
      UserValidation.ageBiggerThan18
    )
    val result = validations(user1)
    println(result.getReason)

  }
}