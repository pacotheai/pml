package paco.pml.common

import javax.inject.Inject
import javax.inject.Provider

/**
 *
 * @since 0.1.0
 */
abstract class ModelProvider<T> implements Provider<T> {

  @Inject
  AppConfig config

  URL getModel(String path) {
    return SystemResources.classpathAsURL("/model/$path")
  }
}
