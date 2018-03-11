package javax.visrec.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * This is a utility class that downloads various datasets such as mnist and stores them in user home folder
 *
 * @author Jyothiprasad Buddha <jyothiprasadb@gmail.com>
 */
public class DataSets {

    /**
     * Downloads the testing data set of mnist and unzips them before returning the folder into which data is extracted
     *
     * @return File object of the folder in which the data is stored
     */
    public static File getMnistTestingDataSet()  {
        File folder = Paths.get(System.getProperty("user.home"), "visrec-datasets", "mnist", "testing").toFile();

        if (!folder.exists()) {
            try {
                mnist(folder.getParentFile(), "mnist_testing_data_png.zip");
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

        return folder;
    }

    /**
     * Downloads the training data set of mnist and unzips them before returning the folder into which data is extracted
     *
     * @return File object of the folder in which the data is stored
     */
    public static File getMnistTrainingDataSet() {
        File folder = Paths.get(System.getProperty("user.home"), "visrec-datasets", "mnist", "training").toFile();

        if (!folder.exists()) {
            try {
                mnist(folder.getParentFile(), "mnist_training_data_png.zip");
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

        return folder;
    }

    private static void mnist(File folder, String githubFileName) throws MalformedURLException {
        if (!folder.exists())
            folder.mkdirs();

        download("https://github.com/jbuddha/VisualRecognitionDataSets/raw/master/" + githubFileName, folder);
    }

    private static void download(String httpsURL, File basePath)  {
        URL url = null;
        try {
            File toFile = new File(basePath, "mnist-dataset.zip");
            url = new URL(httpsURL);
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fos = new FileOutputStream(toFile);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
            unzip(toFile);
            toFile.deleteOnExit();
        } catch (IOException ioException) {
            throw new RuntimeException(ioException);
        }
    }

    private static void unzip(File fileToBeUnzipped) {
        File dir = new File(fileToBeUnzipped.getParent());

        if(!dir.exists())
            dir.mkdirs();

        try {
            ZipFile zipFile = new ZipFile(fileToBeUnzipped.getAbsoluteFile());
            Enumeration<?> enu = zipFile.entries();
            while (enu.hasMoreElements()) {
                ZipEntry zipEntry = (ZipEntry) enu.nextElement();
                String name = zipEntry.getName();

                if (name.contains(".DS_Store") || name.contains("__MACOSX"))
                    continue;

                File file = Paths.get(fileToBeUnzipped.getParent(), name).toFile();
                if (name.endsWith("/")) {
                    file.mkdirs();
                    continue;
                }

                File parent = file.getParentFile();
                if (parent != null) {
                    parent.mkdirs();
                }

                InputStream is = zipFile.getInputStream(zipEntry);
                FileOutputStream fos = new FileOutputStream(file);
                byte[] bytes = new byte[1024];
                int length;
                while ((length = is.read(bytes)) >= 0) {
                    fos.write(bytes, 0, length);
                }
                is.close();
                fos.close();
            }
            zipFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
