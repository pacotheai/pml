package paco.pml.ml

import paco.pml.ml.xor.XORHelloWorld

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
  XORHelloWorld model

  /**
   * Returns an array of {@link String} sentences detected in the text
   * passed as an argument. The whitespace before, between and after the
   * input String is removed
   *
   * @param text The text the sentences are detected from
   * @return an arrayof type {@link Strign} containing found sentences
   * @since 0.1.0
   */
  String trainxor(double [][] input, double [][] ideal) {
    return model.train_xor(input, ideal)
  }
  String solvexor(double [] param) {
    return model.solve_xor(param)
  }

}
