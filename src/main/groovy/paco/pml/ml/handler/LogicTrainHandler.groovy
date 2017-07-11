package paco.pml.ml.handler

import paco.pml.ml.service.LogicService
import ratpack.handling.Context
import ratpack.handling.Handler
import ratpack.rx.RxRatpack
import rx.Observable

import javax.inject.Inject

import static ratpack.jackson.Jackson.json

/**
 *
 * @since 0.1.0
 */
class LogicTrainHandler implements Handler {

  @Inject
  LogicService service

  @Override
  void handle(Context ctx) {
    Map<String,String> payload = ctx.get(Map)

    def operation = ctx.getAllPathTokens().get('operation')

    Observable<String> trainResult = Observable.from(
            service.train(
                    payload.input as double[][],
                    payload.ideal as double[][]
            )
    );

    RxRatpack
      .promise(trainResult)
      .then {
        ctx.render(json(it))
      }
  }
}
