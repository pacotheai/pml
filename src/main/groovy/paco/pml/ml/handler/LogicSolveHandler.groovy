package paco.pml.ml.handler

import paco.pml.ml.service.LogicService

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
class LogicSolveHandler implements Handler {

  @Inject
  LogicService service

  @Override
  void handle(Context ctx) {
    Map<String,String> payload = ctx.get(Map)

    def operation = ctx.getAllPathTokens().get('operation')

    Observable<String> solveResult = Observable.from(
            service.solve(payload.param as double[]));

    RxRatpack
      .promise(solveResult)
      .then {
        ctx.render(json(it))
      }
  }
}
