(ns mametipsum.core
  (:require [compojure.handler :as handler]
            [mametipsum.middleware :as middleware]
            [mametipsum.db :as db]
            [mametipsum.routes :as routes]
            [ring.adapter.jetty :refer [run-jetty]]))

(def app
  (do
    (db/init)
    (handler/api (middleware/api routes/main-routes))))

(defn -main [port]
  (run-jetty app {:port (Integer. port)}))
