(ns mametipsum.core
  (:require
   [compojure.handler :as handler])
  (:use
   [ring.adapter.jetty]
   [hiccup.middleware :only (wrap-base-url)]
   [mametipsum.routes :only (mametipsum-routes)]))

(def app
  (-> (handler/site mametipsum-routes)
      (wrap-base-url)))

(defn -main []
  (let [port (Integer/parseInt (System/getenv "PORT"))]
    (run-jetty app {:port port})))
