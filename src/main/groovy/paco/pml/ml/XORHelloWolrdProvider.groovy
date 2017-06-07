package paco.pml.ml

import paco.pml.common.ModelProvider
import paco.pml.ml.xor.XORHelloWorld

/**
 * Loads an instance of {@link XORHelloWolrd} for the language
 * configured for the application instance.
 *
 * @since 0.1.0
 */
class XORHelloWolrdProvider extends ModelProvider<XORHelloWorld> {

  @Override
  XORHelloWorld get() {
    // getModel(config.model.sentences)
    return new XORHelloWorld()
  }
}
