package paco.pml.ml

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import paco.pml.ml.handler.XORResutltHandler
import paco.pml.ml.handler.XORTrainHandler
import paco.pml.ml.service.XORService
import paco.pml.ml.model.XORModel

/**
 * Loads required classes to deal with sentences
 *
 * @since 0.1.0
 */
class XORModule extends AbstractModule {

  @Override
  void configure() {
    bind(XORModel)
      .toProvider(XORProvider)
      .in(Scopes.SINGLETON)

    bind(XORService).in(Scopes.SINGLETON)
    bind(XORTrainHandler).in(Scopes.SINGLETON)
    bind(XORResutltHandler).in(Scopes.SINGLETON)
  }
}
