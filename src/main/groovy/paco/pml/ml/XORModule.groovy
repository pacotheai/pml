package paco.pml.ml

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import paco.pml.ml.handler.LogicCreateNetworkHandler
import paco.pml.ml.handler.LogicSolveHandler
import paco.pml.ml.handler.LogicTrainHandler
import paco.pml.ml.service.LogicService
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

    bind(LogicService).in(Scopes.SINGLETON)

    bind(LogicCreateNetworkHandler).in(Scopes.SINGLETON)
    bind(LogicTrainHandler).in(Scopes.SINGLETON)
    bind(LogicSolveHandler).in(Scopes.SINGLETON)
  }
}
