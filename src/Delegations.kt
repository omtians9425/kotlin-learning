import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.reflect.KProperty

/**
 * Delegation for property's accessor.
 * For interface's implementation, see [Interface.kt]
 */
fun main() {

    val p = Person("Ken")
    p.emails.forEach { println(it.text) }

    val person4 = Person4()
    person4.setAttributes("name", "Takeshi")
    println(person4.name)

    val person3 = Person3(age = 26, name = "hoge", salary = 10)
    person3.name

}

/*
about lazy initialization
 */
class Email(val text: String)

fun loadEmails(person: Person): List<Email> {
    println("Load emails for ${person.name}")
    return listOf(Email("hogehoge...for${person.name}"), Email("fugafuga..."))
}

//backing property and one time initialization logic is encapsulated
class Person(val name: String) {
    //lambda is invoked when the property is first accessed
    val emails: List<Email> by lazy { loadEmails(this) }
}

/*
on my own version. basically, above is syntax sugar of this code
In above, backing property and one time initialization logic is encapsulated
 */
//class Person(val name: String) {
//    private var _emails: List<Email>? = null //backing property
//
//    val emails: List<Email>
//    get() {
//        if(_emails == null) {
//            _emails = loadEmails(this)
//        }
//        return _emails!!
//    }
//}

/**
 * delegated property sample of change listener
 */
open class PropertyChangeHelper {
    protected val changeSupport = PropertyChangeSupport(this)

    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }
}

/*
getter/setter hard cord version
 */
class Person2(
    val name: String,
    age: Int,
    salary: Int
) : PropertyChangeHelper() {

    var age: Int = age
        set(newValue) {
            val oldValue = field
            field = newValue
            changeSupport.firePropertyChange(
                "age", oldValue, newValue
            )
        }

    var salary: Int = salary
        set(newValue) {
            val oldValue: Int = field
            field = newValue
            changeSupport.firePropertyChange(
                "salary", oldValue, newValue
            )
        }
}

//class ObservableProperty(
//    val propName: String,
//    var propValue: Int,
//    val changeSupport: PropertyChangeSupport
//) {
//    fun getValue(): Int = propValue
//    fun setValue(newValue: Int) {
//        val oldValue = propValue
//        propValue = newValue
//        changeSupport.firePropertyChange(propName, oldValue, newValue)
//    }
//}

/*
extract getter/setter version
 */
//class Person3(val name: String, age: Int, salary: Int) : PropertyChangeHelper() {
//    val _age = ObservableProperty("age", age, changeSupport)
//    var age: Int
//        get() = _age.getValue()
//        set(value) {
//            _age.setValue(value)
//        }
//
//    val _salary = ObservableProperty("salary", salary, changeSupport)
//    var salary: Int
//    get() = _salary.getValue()
//    set(value) {_salary.setValue(value)}
//}

class ObservableProperty(
    var propValue: Int, val changeSupport: PropertyChangeSupport
) {
    operator fun getValue(p: Person3, prop: KProperty<*>): Int = propValue

    operator fun setValue(p: Person3, prop: KProperty<*>, newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }
}

class Person3 (val name: String, age:Int ,salary: Int) : PropertyChangeHelper() {
    var age: Int by ObservableProperty(age, changeSupport)
    var salary: Int by ObservableProperty(salary, changeSupport)
}

class Person4 {
    private val _attributes = hashMapOf<String, String>()

    fun setAttributes(attrName: String, value: String) {
        _attributes[attrName] = value
    }

    val name: String by _attributes
}