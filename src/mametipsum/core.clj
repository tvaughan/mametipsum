(ns mametipsum.core
  (:require [compojure.handler :as handler]
            [compojure.handler :as middleware]
            [mametipsum.db :as db]
            [mametipsum.routes :as routes]
            [ring.adapter.jetty :refer [run-jetty]]))

(def app
  (do
    (db/init)
    (middleware/api (handler/api routes/main-routes))))

(defn -main [port]
  (run-jetty app {:port (Integer. port)}))
