(defproject mametipsum "0.0.2"
  :dependencies
  [[org.clojure/clojure "1.5.1"]
   [ring/ring-jetty-adapter "1.2.0-SNAPSHOT"]
   [ring-middleware-format "0.3.0"]
   [compojure "1.2.0-SNAPSHOT"]]
  :plugins
  [[lein-ring "0.7.5"]]
  :min-lein-version "2.0.0"
  :ring {:handler mametipsum.core/app})
