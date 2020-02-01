package javax.visrec.ml.classification;

import javax.visrec.ml.ClassificationException;
import javax.visrec.ml.ClassifierCreationException;
import javax.visrec.spi.ServiceProvider;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public interface ImageClassifier<IMAGE_CLASS> extends Classifier<IMAGE_CLASS, Map<String, Float>>{

    Map<String, Float> classify(File input) throws ClassificationException;

    Map<String, Float> classify(InputStream input) throws ClassificationException;

}
