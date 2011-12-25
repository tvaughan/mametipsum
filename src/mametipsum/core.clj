(ns mametipsum.core
  (:require
   [ring.adapter.jetty :as jetty]
   [compojure.handler :as handler]
   [mametipsum.db :as db]
   [mametipsum.middleware :as middleware]
   [mametipsum.routes :as routes]))

(def app
  (middleware/api (handler/api routes/main-routes)))

(defn -main []
  (db/init)
  (let [port (Integer/parseInt (get (System/getenv) "PORT" "8080"))]
    (jetty/run-jetty app {:port port})))
