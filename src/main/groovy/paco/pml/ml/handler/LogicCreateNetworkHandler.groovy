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
class LogicCreateNetworkHandler implements Handler {

  @Inject
  LogicService service

  @Override
  void handle(Context ctx) {
    def operation = ctx.getAllPathTokens().get('operation')

    Observable<String> createNetworkResult = Observable.from(
            service.createNetwork()
    );

    RxRatpack
      .promise(createNetworkResult)
      .then {
        ctx.render(json(it))
      }
  }
}
