import paco.pml.ml.XORModule

import static ratpack.groovy.Groovy.ratpack
import static ratpack.groovy.Groovy.groovyMarkupTemplate

import ratpack.groovy.template.MarkupTemplateModule

import paco.pml.init.StartupModule
import paco.pml.ml.XORModule

ratpack {
  bindings {
    module MarkupTemplateModule

    module StartupModule
    module XORModule
  }
}
