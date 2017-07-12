import paco.pml.ml.handler.LogicCreateNetworkHandler
import paco.pml.ml.handler.LogicTrainHandler
import ratpack.handling.Context
import ratpack.handling.Handler

import static ratpack.groovy.Groovy.ratpack
import static ratpack.groovy.Groovy.groovyMarkupTemplate

import ratpack.server.ServerConfigBuilder

import paco.pml.common.AppConfig
import paco.pml.common.HandlerUtils
import paco.pml.ml.handler.LogicSolveHandler

ratpack {

  serverConfig { ServerConfigBuilder config ->
    config
    .port(5050)
    .yaml("pml-conf.yml")
    .require("", AppConfig)
  }

  handlers {
    get {
      render groovyMarkupTemplate("index.gtpl", title: "My Ratpack App")
    }

    files { dir "public" }

/*
    ** http://localhost:5050/api/v1/logic/{operation}/model
    *** http://localhost:5050/api/v1/logic/{operation}/model/inputlayer
    *** http://localhost:5050/api/v1/logic/{operation}/model/outputlayer
    *** http://localhost:5050/api/v1/logic/{operation}/model/hiddenlayer
    *** http://localhost:5050/api/v1/logic/{operation}/model/activation/{function}
    ** http://localhost:5050/api/v1/logic/{operation}/normalize
    *** http://localhost:5050/api/v1/logic/{operation}/normalize/{function}
    ** http://localhost:5050/api/v1/logic/{operation}/model/data
    *** http://localhost:5050/api/v1/logic/{operation}/model/data/{format}
    ** http://localhost:5050/api/v1/logic/{operation}/data/add
    ** http://localhost:5050/api/v1/logic/{operation}/train
    ** http://localhost:5050/api/v1/logic/{operation}/reset
    ** http://localhost:5050/api/v1/logic/{operation}/stop
    ** http://localhost:5050/api/v1/logic/{operation}/save
    *** http://localhost:5050/api/v1/logic/{operation}/save/{pathByUser}
    ** http://localhost:5050/api/v1/logic/{operation}/start
    ** http://localhost:5050/api/v1/logic/{operation}/solve
*/
    prefix('api/v1') {
      prefix('logic/:operation') {
        prefix('model') {
          get(LogicCreateNetworkHandler)
          prefix('inputlayer') {

          }
          prefix('outputlayer') {

          }
          prefix('hiddenlayer') {

          }
          prefix('activation/:function') {

          }
        }
        prefix('normalize/:function'){

        }
        prefix('model/data/:format'){

        }
        prefix('data/add') {

        }
        prefix('train') {
          all(HandlerUtils.createBindingHandler(Map))
          post('me', LogicTrainHandler)
        }
        prefix('reset') {

        }
        prefix('stop') {

        }
        prefix('save/:pathByUser') {

        }
        prefix('start') {

        }
        prefix('solve') {
          all(HandlerUtils.createBindingHandler(Map))
          post('me', LogicSolveHandler)
        }
      }
    }
  }
}
