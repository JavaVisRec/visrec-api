/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.visrec.ml.data.norm;

import javax.visrec.ml.data.DataSet;

/**
 *
 * @author zoran
 */
public interface Normalizer {
        public DataSet normalize(DataSet dataSet);
}
