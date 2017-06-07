package paco.pml.ml

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import paco.pml.ml.xor.XORHelloWorld

/**
 * Loads required classes to deal with sentences
 *
 * @since 0.1.0
 */
class SentenceModule extends AbstractModule {

  @Override
  void configure() {
    bind(XORHelloWorld)
      .toProvider(XORHelloWolrdProvider)
      .in(Scopes.SINGLETON)

    bind(XORService).in(Scopes.SINGLETON)
    bind(XORHandler).in(Scopes.SINGLETON)
  }
}
