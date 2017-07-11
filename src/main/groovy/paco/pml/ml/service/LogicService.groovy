package paco.pml.ml.service

import paco.pml.ml.model.XORModel

import javax.inject.Inject

/**
 * Holds all functions dealing with sentence detection
 *
 * @since 0.1.0
 */
class LogicService {

  /**
   * {@link LogicService}
   *
   * @since 0.1.0
   */
  @Inject
  XORModel model

  /**
   * @since 0.1.0
   */
  String createNetwork() {
    return model.createNetwork()
  }

  String train(double [][] input, double [][] ideal) {
    return model.trainLogicOperation(input, ideal)
  }

  String solve(double [] param) {
    return model.solveLogicOperation(param)
  }

}
