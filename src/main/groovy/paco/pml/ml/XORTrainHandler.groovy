package paco.pml.ml

import ratpack.handling.Context
import ratpack.handling.Handler
import ratpack.rx.RxRatpack
import rx.Observable

import javax.inject.Inject

import static ratpack.jackson.Jackson.json

/**
 * It receives a given text and returns a list of
 * detected sentences
 *
 * @since 0.1.0
 */
class XORTrainHandler implements Handler {

  @Inject
  XORService service

  @Override
  void handle(Context ctx) {
    Map<String,String> payload = ctx.get(Map)

    Observable<String> xor_result = Observable.from(
            service.trainxor(
                    payload.input as double[][],
                    payload.ideal as double[][]));

    RxRatpack
      .promise(xor_result)
      .then {
        ctx.render(json(it))
      }
  }
}
