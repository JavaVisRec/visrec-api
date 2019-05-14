package javax.visrec.util;

/**
 * Shared constants to retrieve and set values in properties.
 *
 * TODO Kevin: "Zoran do we still need this?"
 *      Zoran: Yes
 *
 * @author Zoran Sevarac
 * @since 1.0
 */
public final class VisRecConstants {

    private VisRecConstants() {
        // Prevent instantiation.
    }

    public static final String IMAGE_WIDTH          = "visrec.imageWidth";
    public static final String IMAGE_HEIGHT         = "visrec.imageHeight";
    public static final String SGD_LEARNING_RATE    = "visrec.sgd.learningRate";
    public static final String SGD_MAX_ERROR        = "visrec.sgd.maxError";
    public static final String SGD_MAX_EPOCHS       = "visrec.sgd.maxEpochs";

    public static final String LABELS_FILE          = "visrec.labelsFile";
    public static final String TRAINING_FILE        = "visrec.trainingFile";
    public static final String TEST_FILE            = "visrec.testFile";

    public static final String MODEL_SAVE_TO        = "visrec.model.saveTo";
}
