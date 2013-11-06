(defproject mametipsum "0.0.2"
  :dependencies
  [[org.clojure/clojure "1.5.1"]
   [ring/ring-jetty-adapter "1.2.1"]
   [ring-middleware-format "0.3.1"]
   [compojure "1.2.0-SNAPSHOT"]]
  :plugins
  [[lein-ring "0.8.8"]]
  :min-lein-version "2.0.0"
  :ring {:handler mametipsum.core/app})
