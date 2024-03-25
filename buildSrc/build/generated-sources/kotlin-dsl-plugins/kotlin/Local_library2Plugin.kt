/**
 * Precompiled [local.library2.gradle.kts][Local_library2_gradle] script plugin.
 *
 * @see Local_library2_gradle
 */
public
class Local_library2Plugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("Local_library2_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
