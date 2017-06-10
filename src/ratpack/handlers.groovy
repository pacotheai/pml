import paco.pml.ml.XORTrainHandler

import static ratpack.groovy.Groovy.ratpack

import ratpack.server.ServerConfigBuilder

import paco.pml.common.AppConfig
import paco.pml.common.HandlerUtils
import paco.pml.ml.XORHandler

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
          post('me', XORHandler)
        }
      }
    }
  }
}
