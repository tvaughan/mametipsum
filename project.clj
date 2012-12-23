(defproject mametipsum "0.0.2"
  :dependencies
  [[org.clojure/clojure "1.4.0"]
   [ring/ring-jetty-adapter "1.2.0-SNAPSHOT"]
   [ring-middleware-format "0.2.3"]
   [compojure "1.1.3"]]
  :plugins
  [[lein-ring "0.7.5"]]
  :ring {:handler mametipsum.core/app})
