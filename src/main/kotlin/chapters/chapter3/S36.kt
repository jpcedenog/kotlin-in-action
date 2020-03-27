package chapters.chapter3

fun main(args: Array<String>) {
    saveUser(User(1, "", ""))
    saveUser(User(1, "JP Cedeno", "1234 Small Town, USA"))
}

class User(val id: Int, val name: String, val address: String)

fun User.validateBeforeSave() {
    //validate() is a local function which has access to any field in User
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            /* Note that User's id property can be easily accessed */
            throw IllegalArgumentException("Can't save user $id: empty $fieldName")
        }
    }

    validate(name, "Name")
    validate(address, "Address")
    // Save user to the database
}

fun saveUser(user: User) {
    try {
        user.validateBeforeSave()
        println("User '${user.name}' saved correctly!")
    } catch (e: Exception) {
        println(e.message)
    }
}
