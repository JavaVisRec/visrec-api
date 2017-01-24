package visrec.impl.dl4j;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.datavec.image.loader.ImageLoader;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.api.ndarray.INDArray;
import visrec.classifier.ImageClassifier;
import visrec.util.BufferedImageFactory;
import visrec.util.ImageRecognitionResults;
import visrec.util.ImageRecognitionResult;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class Dl4jImageClassifier extends ImageClassifier<BufferedImage> {
     MultiLayerNetwork neuralNet;

    public Dl4jImageClassifier(String fileName) throws IOException {
        neuralNet = ModelSerializer.restoreMultiLayerNetwork(fileName);
        setImageFactory(new BufferedImageFactory());
    }
    
    public Dl4jImageClassifier(File file) throws IOException {
        neuralNet = ModelSerializer.restoreMultiLayerNetwork(file);
        setImageFactory(new BufferedImageFactory());
    }    

    public Dl4jImageClassifier(MultiLayerNetwork neuralNet) {
        this.neuralNet = neuralNet;
        setImageFactory(new BufferedImageFactory());
    }
        

    @Override
    public ImageRecognitionResults classify(BufferedImage sample) {
        ImageLoader imageLoader = new ImageLoader();                
        INDArray input =  imageLoader.asRowVector(sample);     
        
        // feed netwrk with image       
        INDArray output = neuralNet.output(input);
        // how to get labels from network?
        
        // get output
        // get label
        ImageRecognitionResults results = new ImageRecognitionResults();
        // transform here binary network outpit to ImageRecognitionResult
        for(int i = 0; i< output.getRow(0).length(); i++ ) {
            double score = output.getFloat(0, i);
            if (score > 0.5) {
                ImageRecognitionResult r = new ImageRecognitionResult( (i+1)+" ", score ); // get label here
                results.add(r);
            }
        }
        
        return results;
    }

    @Override
    public void buildClassifier(Map data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map classDistribution(BufferedImage instance) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
