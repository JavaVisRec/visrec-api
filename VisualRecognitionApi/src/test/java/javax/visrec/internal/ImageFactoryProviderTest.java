package javax.visrec.internal;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.visrec.NoSuchGenericTypeArgumentException;
import javax.visrec.util.Image;
import javax.visrec.util.ImageFactory;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test suite to test {@link ImageFactoryProvider}. Check for implementations used in this test suite
 * in {@code resources/META-INF/services/javax.visrec.util.ImageFactory}.
 *
 * This test suite uses the reflection API to access the no-args constructor of the {@link ImageFactoryProvider}
 * to refresh the state and cache of the class for each unit test to prevent data to be shared between unit tests.
 *
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
public class ImageFactoryProviderTest {

    private ImageFactoryProvider serviceLoader;

    private static Constructor<ImageFactoryProvider> constructor = null;

    @BeforeClass
    public static void setUpClass() throws NoSuchMethodException {
        constructor = ImageFactoryProvider.class.getDeclaredConstructor();
        constructor.setAccessible(true);
    }

    @Before
    public void setUp() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        serviceLoader = constructor.newInstance();
    }

    @After
    public void tearDown() {
        serviceLoader = null;
    }

    /**
     * Test the presence of the standard implemented {@link ImageFactory} using {@link BufferedImage} as image class.
     */
    @Test
    public void testBufferedImageFactory() {
        final Optional<ImageFactory<BufferedImage>> imageFactoryOptional =
                serviceLoader.findImageFactory(BufferedImage.class);
        assertTrue(imageFactoryOptional.isPresent());
    }

    /**
     * Test {@link ExampleImageFactory} implementation of {@link ImageFactory} which is properly
     * implemented.
     */
    @Test
    public void testExampleImageFactory() throws IOException {
        final Optional<ImageFactory<ExampleImageFactory.ExampleImage>> imageFactoryOptional =
                serviceLoader.findImageFactory(ExampleImageFactory.ExampleImage.class);
        assertTrue(imageFactoryOptional.isPresent());
        assertNotNull(imageFactoryOptional.get().getImage((File) null));
    }

    /**
     * Test an {@link ImageFactory} ({@link MissingAnnotationImageFactory}) which does not have an annotation.
     * If the implementation is not annotated then the the implementation will not be found by the utility class.
     */
    @Test
    public void testImageFactoryWithoutAnnotation() {
        // The implementation uses "Void" as the generic type
        final Optional<ImageFactory<Void>> imageFactory =
                serviceLoader.findImageFactory(Void.class);
        assertFalse(imageFactory.isPresent());
    }

    /**
     * Test an {@link ImageFactory} implementation which has does not have the same Class used as generic type
     * and value of the {@link Image} annotation.
     */
    @Test(expected = NoSuchGenericTypeArgumentException.class)
    public void testInvalidImageClassInImageFactory() {
        serviceLoader.findImageFactory(InvalidExampleImageFactory.InvalidExampleImage.class);
    }
}
