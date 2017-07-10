package paco.pml.ml.service

import paco.pml.ml.model.XORModel

import javax.inject.Inject

/**
 * Holds all functions dealing with sentence detection
 *
 * @since 0.1.0
 */
class XORService {

  /**
   * {@link XORService}
   *
   * @since 0.1.0
   */
  @Inject
  XORModel model

  /**
   * @since 0.1.0
   */
  String trainxor(double [][] input, double [][] ideal) {
    return model.train_xor(input, ideal)
  }

  String solvexor(double [] param) {
    return model.solve_xor(param)
  }

}
