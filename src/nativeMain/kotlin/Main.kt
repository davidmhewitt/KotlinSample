import nativex.gio.dsl.onCreateUI
import nativex.gtk.dsl.*

fun main() {
    val printHello = {
        println("Hello World!")
    }
    application("io.github.davidmhewitt.KotlinSample") {
        onCreateUI {
            applicationWindow {
                title = "Window"
                borderWidth = 10u
                grid {
                    button("Button 1", 0, 0, 1, 1) {
                        onClicked { printHello() }
                    }
                    button("Button 2", 1, 0, 1, 1) {
                        onClicked { printHello() }
                    }
                    button("Quit", 0, 1, 2, 1) {
                        onClicked {
                            // Be careful of the context when you invoke destroy
                            this@applicationWindow.destroy()
                        }
                    }
                }
            }.showAll()
        }
    }
}