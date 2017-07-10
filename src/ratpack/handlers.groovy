import paco.pml.ml.handler.XORTrainHandler

import static ratpack.groovy.Groovy.ratpack
import static ratpack.groovy.Groovy.groovyMarkupTemplate

import ratpack.server.ServerConfigBuilder

import paco.pml.common.AppConfig
import paco.pml.common.HandlerUtils
import paco.pml.ml.handler.XORResutltHandler

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

    prefix('api/v1') {
      prefix('xor') {
        prefix('train') {
          all(HandlerUtils.createBindingHandler(Map))
          post('me', XORTrainHandler)
        }
        prefix('solve') {
          all(HandlerUtils.createBindingHandler(Map))
          post('me', XORResutltHandler)
        }
      }
    }
  }
}
