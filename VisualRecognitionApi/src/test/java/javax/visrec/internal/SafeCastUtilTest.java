package javax.visrec.internal;

import org.junit.Test;

import javax.visrec.NoSuchGenericInterfaceException;
import javax.visrec.NoSuchGenericTypeArgumentException;
import javax.visrec.util.BufferedImageFactory;
import javax.visrec.util.ImageFactory;
import java.awt.image.BufferedImage;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Test suite to test the {@link SafeCastUtil} class.
 *
 * @author Kevin Berendsen <info@kevinberendsen.nl>
 */
public class SafeCastUtilTest {

    /**
     * Testing the happy flow of safely casting an ImageFactory.
     */
    @Test
    public void testCanSafelyCastImageFactory() {
        final List<ImageFactory> imageFactories = ServiceProvider.getInstance().findImplementations(ImageFactory.class);
        ImageFactory impl = null;
        for (ImageFactory factory : imageFactories) {
            if (factory instanceof BufferedImageFactory) {
                impl  = factory;
                break;
            }
        }
        SafeCastUtil.canSafelyCast(impl, ImageFactory.class, BufferedImage.class);
    }

    /**
     * Testing the bad flow of not being able to find the proper generic type arguments
     * on the object. The object was given BufferedImage but argument is String.
     */
    @Test(expected = NoSuchGenericTypeArgumentException.class)
    public void testCanNotSafelyCastList() {
        final List<ImageFactory> imageFactories = ServiceProvider.getInstance().findImplementations(ImageFactory.class);
        ImageFactory impl = null;
        for (ImageFactory factory : imageFactories) {
            if (factory instanceof BufferedImageFactory) {
                impl  = factory;
                break;
            }
        }
        SafeCastUtil.canSafelyCast(impl, ImageFactory.class, String.class);
    }

    /**
     * Testing the bad flow of not being able to find the interface on the object.
     * The object given is an ImageFactory but interface is List.
     */
    @Test(expected = NoSuchGenericInterfaceException.class)
    public void testInterfaceNotFoundOnObject() {
        final List<ImageFactory> imageFactories = ServiceProvider.getInstance().findImplementations(ImageFactory.class);
        ImageFactory impl = null;
        for (ImageFactory factory : imageFactories) {
            if (factory instanceof BufferedImageFactory) {
                impl  = factory;
                break;
            }
        }
        SafeCastUtil.canSafelyCast(impl, List.class, BufferedImage.class);
    }

    /**
     * Testing the preconditions of {@link SafeCastUtil#canSafelyCast(Object, Class, Class[])}
     */
    @Test
    public void testPreconditions() {
        final List<ImageFactory> imageFactories = ServiceProvider.getInstance().findImplementations(ImageFactory.class);
        ImageFactory impl = null;
        for (ImageFactory factory : imageFactories) {
            if (factory instanceof BufferedImageFactory) {
                impl  = factory;
                break;
            }
        }

        try {
            SafeCastUtil.canSafelyCast(null, ImageFactory.class, BufferedImage.class);
            fail("May not accept null as object");
        } catch (NullPointerException e) {
            // It's good.
        }

        try {
            SafeCastUtil.canSafelyCast(impl, null, BufferedImage.class);
            fail("May not accept null as interface");
        } catch (NullPointerException e) {
            // It's good.
        }

        try {
            SafeCastUtil.canSafelyCast(impl, BufferedImageFactory.class, BufferedImage.class);
            fail("May not accept non interface as genericIntf");
        } catch (IllegalArgumentException e) {
            // It's good.
        }

        try {
            SafeCastUtil.canSafelyCast(impl, BufferedImageFactory.class);
            fail("May not accept empty or null arguments as type arguments");
        } catch (IllegalArgumentException e) {
            // It's good.
        }
    }
}
