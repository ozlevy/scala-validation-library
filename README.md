# User Validation Library
 
## Overview
 
This library provides a flexible and reusable framework for user validation in Scala. It allows developers to define custom validation rules, combine them using logical operations (`AND`, `OR`), and validate user objects with ease.
 
## Features
 
- **Validation Framework**: Easily define and apply validation rules.
- **Logical Combinations**: Combine rules with `AND`/`OR` logic.
- **Customizable**: Extend and adapt validations to specific requirements.
- **Lightweight**: Focused on simplicity and performance.
- **Built with Scala**: Leverages Scala’s functional programming capabilities.
 
## Project Structure
 
```plaintext
src/main/scala/il/ac/hit/validation/
  - ValidationResult.scala        # Base trait for validation results
  - Valid.scala                   # Represents a successful validation result
  - Invalid.scala                 # Represents a failed validation result
  - UserValidation.scala          # Trait and utilities for user validation logic
  - User.scala                    # Represents a user object
```
 
## Getting Started
 
### Prerequisites
 
- Scala 2.13 or higher
- SBT (Scala Build Tool)
 
### Installation
 
1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```
 
2. Navigate to the project directory:
   ```bash
   cd user-validation-library
   ```
 
3. Compile the project:
   ```bash
   sbt compile
   ```
 
4. Package the project as a JAR file:
   ```bash
   sbt package
   ```
   The JAR file will be located in `target/scala-<version>/`.
 
### Usage
 
#### Import the Library
 
1. Copy the generated `.jar` file to your project's `lib/` directory.
2. Add the following to your `build.sbt` file:
   ```scala
   unmanagedBase := baseDirectory.value / "lib"
   ```
 
3. Reload the SBT project:
   ```bash
   sbt reload
   ```
 
#### Example Code
 
Here’s an example of how to use the library:
 
```scala
import il.ac.hit.validation._
 
object Main extends App {
  val user = new User("john_doe", "john.doe@example.com", "Password123$", 25)
 
  val validation = UserValidation.emailEndsWithIL and UserValidation.ageBiggerThan18
  val result = validation(user)
 
  println(s"Is valid: ${result.isValid}")
  result.getReason.foreach(reason => println(s"Failure reason: $reason"))
}
```
 
### Default Validations
 
| Validation Rule                                   | Description                                    |
|--------------------------------------------------|------------------------------------------------|
| `emailEndsWithIL`                                | Email must end with `.il`.                    |
| `emailLengthBiggerThan10`                        | Email length must be greater than 10.         |
| `passwordLengthBiggerThan8`                      | Password length must be greater than 8.       |
| `passwordIncludesLettersNumbersOnly`             | Password must include only letters and numbers. |
| `passwordIncludesDollarSign`                     | Password must include a `$` character.        |
| `passwordIsDifferentFromUsername`                | Password must be different from the username. |
| `ageBiggerThan18`                                | User’s age must be greater than 18.           |
| `usernameLengthBiggerThan8`                      | Username length must be greater than 8.       |
 
### Tests
 
Unit tests can be written using `ScalaTest` or any other testing framework. Here’s an example of a test case:
 
```scala
import org.scalatest.funsuite.AnyFunSuite
import il.ac.hit.validation._
 
class UserValidationTest extends AnyFunSuite {
  test("emailEndsWithIL validation") {
    val user = new User("test", "test@example.com", "password", 20)
    assert(UserValidation.emailEndsWithIL(user).isValid === false)
  }
}
```
 
 
## Contributions
 
Contributions are welcome! Feel free to open issues, suggest features, or submit pull
requests.
 
 
## Contact
 
For questions or feedback, please contact 
[Oz Levy](mailto:ozlevy2@gmail.com)
[Dor Slagter](mailto:dslagter99@gmail.com)
