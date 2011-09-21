(ns mametipsum.core
  (:use
   [ring.adapter.jetty]
   [hiccup.middleware :only (wrap-base-url)]
   [mametipsum.routes])
  (:require
   [compojure.handler :as handler]))

(def mametipsum-app
  (-> (handler/site mametipsum-routes)
      (wrap-base-url)))

(defn -main []
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "8080"))]
    (run-jetty mametipsum-app {:port port})))
