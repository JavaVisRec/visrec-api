package javax.visrec.ml.classification;


import java.io.File;
import java.io.InputStream;
import java.util.Map;

public interface ImageClassifier<IMAGE_CLASS> extends Classifier<IMAGE_CLASS, Map<String, Float>>{

    Map<String, Float> classify(File input);

    Map<String, Float> classify(InputStream input);

}
