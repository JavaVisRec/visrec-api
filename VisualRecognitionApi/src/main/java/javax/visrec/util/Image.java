package javax.visrec.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines which Class is associated with an implementation of {@link ImageFactory}.
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Image {
    Class<?> value();
}
