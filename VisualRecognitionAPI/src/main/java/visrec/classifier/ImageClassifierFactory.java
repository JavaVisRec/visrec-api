package visrec.classifier;

import java.util.Map;

public interface ImageClassifierFactory {
        public ImageClassifier createImageClassifier(Map properties);
}
