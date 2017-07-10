/*
 * Encog(tm) Java Examples v3.4
 * http://www.heatonresearch.com/encog/
 * https://github.com/encog/encog-java-examples
 *
 * Copyright 2008-2016 Heaton Research, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *   
 * For more information on Heaton Research copyrights, licenses 
 * and trademarks visit:
 * http://www.heatonresearch.com/copyright
 */
package paco.pml.ml.model;

import org.encog.Encog;
import org.encog.engine.network.activation.ActivationReLU;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.TrainingContinuation;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.persist.EncogDirectoryPersistence;

import java.io.File;
import java.util.ArrayList;

/**
 * XOR: This example is essentially the "Hello World" of neural network
 * programming.  This example shows how to construct an Encog neural
 * network to predict the output from the XOR operator.  This example
 * uses backpropagation to train the neural network.
 * 
 * This example attempts to use a minimum of Encog features to create and
 * train the neural network.  This allows you to see exactly what is going
 * on.  For a more advanced example, that uses Encog factories, refer to
 * the XORFactory example.
 * 
 */
public class XORModel {

	BasicNetwork network = new BasicNetwork();

	public void train_xor(final double input[][], final double ideal[][], final double param[]) {

		// create a neural network, without using a factor
		network.addLayer(new BasicLayer(null, true, 2));
		network.addLayer(new BasicLayer(new ActivationReLU(), true, 5));
		network.addLayer(new BasicLayer(new ActivationSigmoid(), false, 1));
		network.getStructure().finalizeStructure();
		network.reset();

		// create training data
		MLDataSet trainingSet = new BasicMLDataSet(input, ideal);

		// train the neural network
		final ResilientPropagation train = new ResilientPropagation(network, trainingSet);

		EncogDirectoryPersistence.saveObject(new File("/home/jose/prueba.eg"), network);

//		TrainingContinuation trainPaused = train.pause();

		int epoch = 1;

		do {
			train.iteration();
			System.out.println("Epoch #" + epoch + " Error:" + train.getError());
			epoch++;
		} while (train.getError() > 0.01);
		train.finishTraining();
	}


	public String solve_xor(final double param[]) {

		StringBuffer result = new StringBuffer();

		// test the neural network
		System.out.println("Neural Network Results:");

		BasicMLData toSolve = new BasicMLData(param);

		final MLData output = network.compute(toSolve);
		result.append(toSolve.getData(0)).append(",").append(toSolve.getData(1)).
				append(", actual=").append(output.getData(0)).append(",ideal=").append(toSolve.getData(0));

		Encog.getInstance().shutdown();

		return result.toString();
	}
}