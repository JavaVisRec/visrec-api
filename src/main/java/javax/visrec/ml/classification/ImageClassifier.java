package javax.visrec.ml.classification;


import java.io.InputStream;
import java.nio.file.Path;
import java.util.Map;

/**
 * Classifier interface specialized in image classification
 *
 * @param <IMAGE_CLASS> type of input objects to classify (eg. User, Product, Transaction, Image, etc.)
 * @author Zoran Sevarac
 * @since 1.0
 */
public interface ImageClassifier<IMAGE_CLASS> extends Classifier<IMAGE_CLASS, Map<String, Float>> {

    /**
     * Classify the input and get a map of classification results as output
     *
     * @param input {@link Path} to use as input
     * @return {@code Map} with key as classification label and with value as accuracy percentage of likelihood
     * @throws ClassificationException if the file couldn't be found or classified
     */
    Map<String, Float> classify(Path input) throws ClassificationException;

    /**
     * Classify the input and get a map of classification results as output
     *
     * @param input {@link InputStream} to use as input
     * @return {@code Map} with key as classification label and with value as accuracy percentage of likelihood
     * @throws ClassificationException if input couldn't be classified
     */
    Map<String, Float> classify(InputStream input) throws ClassificationException;

}
