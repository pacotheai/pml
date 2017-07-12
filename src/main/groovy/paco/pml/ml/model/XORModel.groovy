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
import org.encog.persist.EncogDirectoryPersistence
import org.encog.util.obj.SerializeObject

import static util.Looper.loop;

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

	private static final String PATH_BY_USER = "/home/jose/test.eg";

	public String createNetwork() {
		BasicNetwork network = new BasicNetwork();
		network.addLayer(new BasicLayer(null,true,2));
		network.addLayer(new BasicLayer(new ActivationReLU(),true,5));
		network.addLayer(new BasicLayer(new ActivationSigmoid(),false,1));
		network.getStructure().finalizeStructure();
		network.reset();

//		EncogDirectoryPersistence.saveObject(new File(PATH_BY_USER), network)
		SerializeObject.save(new File(PATH_BY_USER), network)

		return ""
	}

	public String trainLogicOperation(final double [][] input, final double [][] ideal) {

//		BasicNetwork network = (BasicNetwork)EncogDirectoryPersistence.loadObject(new File(PATH_BY_USER));
		BasicNetwork network = (BasicNetwork)SerializeObject.load(new File(PATH_BY_USER));

		// create training data
		MLDataSet trainingSet = new BasicMLDataSet(input, ideal);

		// train the neural network
		final ResilientPropagation train = new ResilientPropagation(network, trainingSet);

//		TrainingContinuation trainPaused = train.pause();

		int epoch = 1;
		loop {
			train.iteration()
			System.out.println("Epoch #" + epoch + " Error:" + train.getError())
			epoch++
		} until {(train.getError() < 0.01)}
		train.finishTraining();

//		EncogDirectoryPersistence.saveObject(new File(PATH_BY_USER), network)
		SerializeObject.save(new File(PATH_BY_USER), network)

		return ""
	}


	public String solveLogicOperation(final double [] param) {

		StringBuffer result = new StringBuffer();

		BasicMLData toSolve = new BasicMLData(param);

//		BasicNetwork network = (BasicNetwork)EncogDirectoryPersistence.loadObject(new File(PATH_BY_USER));
		BasicNetwork network = (BasicNetwork)SerializeObject.load(new File(PATH_BY_USER));

		final MLData output = network.compute(toSolve);

		result.append(
				toSolve.getData(0)).append(",").append(toSolve.getData(1))
				.append(", actual=").append(output.getData(0));

		Encog.getInstance().shutdown();

		return result.toString();
	}
}
