package paco.pml.ml

import paco.pml.common.ModelProvider
import paco.pml.ml.model.XORModel

/**
 *
 * @since 0.1.0
 */
class XORProvider extends ModelProvider<XORModel> {

  @Override
  XORModel get() {
    // getModel(config.model.sentences)
    return new XORModel()
  }
}
