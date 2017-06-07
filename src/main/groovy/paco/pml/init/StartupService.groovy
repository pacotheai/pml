package paco.pml.init

import ratpack.rx.RxRatpack
import ratpack.service.Service
import ratpack.service.StartEvent

class StartupService implements Service {
  @Override
  void onStart(StartEvent event) {
    RxRatpack.initialize()
  }
}
