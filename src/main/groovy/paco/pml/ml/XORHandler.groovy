package paco.pml.ml

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
class XORHandler implements Handler {

  @Inject
  XORService service

  @Override
  void handle(Context ctx) {
    Map<String,String> payload = ctx.get(Map)
    Observable<String> xor_result = Observable.from(service.xor(payload.text))

    RxRatpack
      .promise(xor_result)
      .then {
        ctx.render(json(it))
      }
  }
}
