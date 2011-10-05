(ns mametipsum.core
  (:use
   [ring.adapter.jetty]
   [hiccup.middleware :only (wrap-base-url)]
   [mametipsum.db]
   [mametipsum.routes])
  (:require
   [compojure.handler :as handler]))

(def mametipsum-app
  (wrap-base-url (handler/site mametipsum-routes)))

(defn -main []
  (init-db)
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "8080"))]
    (run-jetty mametipsum-app {:port port})))
