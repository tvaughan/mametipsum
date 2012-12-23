(ns mametipsum.core
  (:use
   [ring.adapter.jetty :only [run-jetty]])
  (:require
   [compojure.handler :as handler]
   [mametipsum.db :as db]
   [mametipsum.middleware :as middleware]
   [mametipsum.routes :as routes]))

(def app
  (do
    (db/init)
    (middleware/api (handler/api routes/main-routes))))

(defn -main [port]
  (run-jetty app {:port (Integer. port)}))
