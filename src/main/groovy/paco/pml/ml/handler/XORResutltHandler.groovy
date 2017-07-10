package paco.pml.ml.handler

import paco.pml.ml.service.XORService

import static ratpack.jackson.Jackson.json

import javax.inject.Inject
import ratpack.handling.Handler
import ratpack.handling.Context
import ratpack.rx.RxRatpack
import rx.Observable

/**
 * It receives a given text and returns a list of
 * detected sentences
 *
 * @since 0.1.0
 */
class XORResutltHandler implements Handler {

  @Inject
  XORService service

  @Override
  void handle(Context ctx) {
    Map<String,String> payload = ctx.get(Map)

    Observable<String> xor_result = Observable.from(
            service.solvexor(payload.param as double[]));

    RxRatpack
      .promise(xor_result)
      .then {
        ctx.render(json(it))
      }
  }
}
